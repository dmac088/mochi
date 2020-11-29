package io.nzbee.search;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.SortedNumericSortField;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.hibernate.search.query.facet.Facet;
import org.hibernate.search.query.facet.FacetCombine;
import org.hibernate.search.query.facet.FacetSortOrder;
import org.hibernate.search.query.facet.FacetingRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.google.common.collect.Lists;
import io.nzbee.search.facet.SearchFacetDiscrete;
import io.nzbee.search.facet.SearchFacetHelper;
import io.nzbee.search.facet.SearchFacetRange;
import io.nzbee.search.facet.SearchFacetWithFieldHelper;
import io.nzbee.entity.product.IProductService;
import io.nzbee.entity.product.ProductDTO;
import io.nzbee.entity.product.ProductEntity;
import io.nzbee.Constants;
import io.nzbee.entity.PageableUtil;

@Service
public class SearchServiceImpl implements ISearchService {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	private static final String TITLE_EDGE_NGRAM_INDEX = "edgeNGramTitle";

	private static final String TITLE_NGRAM_INDEX = "nGramTitle";

	@Autowired
	private ApplicationContext appContext;

	@Autowired
	private IFacetServices facetServices;
	
	@Autowired
	private IProductService productService;

	@PersistenceContext(unitName = "mochiEntityManagerFactory")
	private EntityManager em;

	private String cleanLocale(String locale) {
		return locale.substring(0, 2).toUpperCase() + locale.substring(3, 5).toUpperCase();
	}

	private Set<Facet> processFacet(String locale, 
									String currency, 
									QueryBuilder qb,
									FullTextQuery jpaQuery, 
									Set<Facet> facets, 
									Facet selectedFacet,
									Set<SearchFacetHelper> setSearchFacetHelper) {

		Set<SearchFacetWithFieldHelper> lf = facets.stream().map(f -> {
			SearchFacetWithFieldHelper sp = new SearchFacetWithFieldHelper();
			sp.setFacetingName(f.getFacetingName());
			sp.setFieldName(f.getFieldName());
			sp.setType(f.getClass().getSimpleName());
			return sp;
		}).collect(Collectors.toSet());

		facets.removeAll(facets.stream().filter(f -> !selectedFacet.getFacetingName().contains(f.getFacetingName()))
				.collect(Collectors.toSet()));

		//aggregate the codes of the discrete facets
		lf.stream()
		  .filter(f -> f.getType().equals("SimpleFacet"))
		  .map(f -> f.getFacetingName()).collect(Collectors.toSet()).stream().forEach(s -> {

			Set<String> ss = new HashSet<String>();
			SearchFacetHelper sfh = new SearchFacetHelper();
			
			lf.stream().filter(f -> f.getFacetingName().equals(s)).forEach(f -> {
				this.getDiscreteFacets(locale, 
									   currency,
									   qb, 
									   jpaQuery, 
									   f.getFacetingName(), 
									   f.getFieldName(), 
									   facets,
									   ss);

			});
			sfh.setFacetingName(s);
			sfh.setCodes(ss);
			sfh.setType("SimpleFacet");
			setSearchFacetHelper.add(sfh);
		});
		
		//for range facets there is no need to aggregate
		lf.stream().filter(f -> f.getType().equals("RangeFacetImpl")).forEach(f -> {
			SearchFacetHelper sfh = new SearchFacetHelper();
			sfh.setCodes(null);
			sfh.setFacetingName(f.getFacetingName());
			sfh.setType(f.getType());
			setSearchFacetHelper.add(sfh);
		});
		return facets;
	}

	private Set<SearchFacetHelper> aggregateFacetHelpers(Set<SearchFacetHelper> lsfh, String type) {
		Set<SearchFacetHelper> newLsfh = new HashSet<SearchFacetHelper>();
		lsfh.stream().map(sfh -> sfh.getFacetingName()).collect(Collectors.toSet()).stream().forEach(f -> {
			Set<String> sstr = new HashSet<String>();
			lsfh.stream().filter(fh -> fh.getFacetingName().equals(f)).collect(Collectors.toSet()).stream()
					.forEach(l -> {
						sstr.addAll(l.getCodes());
					});

			SearchFacetHelper nsfh = new SearchFacetHelper();
			nsfh.setFacetingName(f);
			nsfh.setCodes(sstr);
			nsfh.setType(type);
			newLsfh.add(nsfh);
		});
		return newLsfh;
	}

	private Set<SearchFacetHelper> initializeFacetHelpers(Set<SearchFacetHelper> lsfh, Set<Facet> facets) {
		facets.stream().forEach(f -> {
			SearchFacetHelper sfh = new SearchFacetHelper();
			sfh.setFacetingName(f.getFacetingName());
			sfh.setType(f.getClass().getSimpleName());
			lsfh.add(sfh);
		});
		return lsfh;
	}
	
	private List<Facet> getRangeFacets(QueryBuilder qb, 
									   FullTextQuery jpaQuery,
									   String locale, 
									   String currency, 
									   String name, 
									   String onField,
									   Double below,
									   Double froma,
									   Double toa,
									   Double fromb,
									   Double tob, 
									   Double above) {

		FacetingRequest facetRequest = 
				qb.facet().name(name)
				.onField(onField) 
				.range()
				.below(below)
				.from(froma)
				.to(toa)
				.from(fromb)
				.to(tob)
				.above(above)
				.orderedBy(FacetSortOrder.RANGE_DEFINITION_ORDER)
				.createFacetingRequest();

		jpaQuery.getFacetManager().enableFaceting(facetRequest);
		return jpaQuery.getFacetManager().getFacets(name);
	}
	

	private Set<String> getDiscreteFacets(String locale, 
										  String currency, 
										  QueryBuilder qb,
										  FullTextQuery jpaQuery, 
										  String facetingName, 
										  String fieldReference,
										  Set<Facet> facets, 
										  Set<String> ss) {

		// create a category faceting request for the base level
		FacetingRequest facetRequest = qb.facet()
									 	 .name(facetingName)
									 	 .onField(fieldReference) // in category class
									 	 .discrete()
									 	 .orderedBy(FacetSortOrder.COUNT_DESC)
									 	 .includeZeroCounts(false)
									 	 .createFacetingRequest();

		// add all the base level facets to categoryFacets List
		jpaQuery.getFacetManager().enableFaceting(facetRequest);

		// Get all the id's of the facets in one go
		facets.addAll(jpaQuery.getFacetManager().getFacets(facetingName));

		Set<String> uniqueCodes = new HashSet<String>();
		Set<String> uniqueFieldRefs = new HashSet<String>();

		// Add all the category codes up the hierarchy
		facets.stream().filter(f -> f.getFacetingName().equals(facetingName)).forEach(f -> {
			// this is concatenating both brand and category codes
			List<String> codes = Arrays.asList(f.getValue().split("/")).stream().filter(o -> !o.isEmpty())
					.collect(Collectors.toList());

			uniqueCodes.addAll(codes);

			// if codes array length is > 1 then the facet is hierarchical
			if (codes.size() > 1) {
				getFieldRefs(f, codes, uniqueFieldRefs);
			}

		});

		ss.addAll(uniqueCodes);

		uniqueFieldRefs.stream().forEach(fr -> {
			FacetingRequest frq = qb.facet()
									.name(facetingName)
									.onField(fr).discrete()
									.orderedBy(FacetSortOrder.COUNT_DESC)
									.includeZeroCounts(false)
									.createFacetingRequest();

			jpaQuery.getFacetManager().enableFaceting(frq);
			facets.addAll(jpaQuery.getFacetManager().getFacets(facetingName));
		});

		// get the object array for the ids in previous step
		return ss;
	}

	private void getFieldRefs(Facet f, List<String> codes, final Set<String> fieldRefs) {
		for (int i = 0; i < codes.size(); i++) {
			List<String> ls = codes.subList(0, i + 1).stream().filter(o -> !(o.isEmpty())).collect(Collectors.toList());
			String prefix = f.getFieldName().split("\\.")[0] + "." + f.getFieldName().split("\\.")[1];
			String suffix = f.getFieldName().split("\\.")[f.getFieldName().split("\\.").length - 1];
			int numParents = codes.size() - ls.size();
			String newFieldReference = prefix + StringUtils.repeat(".parent", numParents) + "." + suffix;
			fieldRefs.add(newFieldReference);
		}
	}
	
	private void getPriceFacets(String locale, 
								String currency, 
								List<ProductEntity> results,
								Set<Facet> facets,
								QueryBuilder queryBuilder, 
								FullTextQuery jpaQuery
								) {
		
		Double minPrice = new Double(0), maxPrice = new Double(0);
		
		if (currency.equals(Constants.currencyHKD)) maxPrice = results.stream().findFirst().get().getCurrentMarkdownPriceHKD();
		if (currency.equals(Constants.currencyUSD)) maxPrice = results.stream().findFirst().get().getCurrentMarkdownPriceUSD();
		if (currency.equals(Constants.currencyHKD)) minPrice = Lists.reverse(results).stream().findFirst().get().getCurrentMarkdownPriceHKD();
		if (currency.equals(Constants.currencyUSD)) minPrice = Lists.reverse(results).stream().findFirst().get().getCurrentMarkdownPriceUSD();
				  		  
		Double inc = (maxPrice > 0) ? (maxPrice - ((minPrice.equals(maxPrice)) ? 0 : minPrice)) / 4 : maxPrice;
		inc = new BigDecimal(inc).setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
		
		Double	below = inc,
				froma = (new BigDecimal(inc + new Double(0.01)).setScale(2, BigDecimal.ROUND_DOWN).doubleValue()),
			   	toa = 	(new BigDecimal(inc * 2).setScale(2, BigDecimal.ROUND_DOWN).doubleValue()),
			   	fromb = (new BigDecimal(toa + new Double(0.01)).setScale(2, BigDecimal.ROUND_DOWN).doubleValue()),
				tob = 	(new BigDecimal(inc * 4).setScale(2, BigDecimal.ROUND_DOWN).doubleValue()), 
				above = tob;
		
		facets.addAll(this.getRangeFacets(	queryBuilder, 
											jpaQuery, 
											locale, 
											currency,
											"price", 
											"currentMarkdownPrice" + currency + "Facet",
											below, 
											froma, 
											toa, 
											fromb, 
											tob, 
											above));
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public PageImpl<ProductDTO> findAll(String lcl, String currency, String categoryDesc, String searchTerm, int page,
			int size, String sortBy, Set<io.nzbee.search.facet.IFacet> facetPayload,
			Set<io.nzbee.search.facet.IFacet> returnFacets) {

		FullTextEntityManager fullTextEntityManager = org.hibernate.search.jpa.Search.getFullTextEntityManager(em);

		String transLcl = cleanLocale(lcl);

		QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory()
														 .buildQueryBuilder()
														 .forEntity(ProductEntity.class)
														 .overridesForField("productDesc", lcl)
														 .overridesForField("productLongDesc", lcl)
														 .overridesForField("product.brand.brandDesc", lcl)
														 .overridesForField("product.categories.categoryDesc", lcl)
														 .overridesForField("product.categories.parent.categoryDesc", lcl)
														 .overridesForField("product.categories.parent.parent.categoryDesc", lcl)
														 .overridesForField("product.tags.tagDesc", lcl)
																 .get();
		

		// this is a Lucene query using the Lucene api
		Query searchQuery = queryBuilder.bool()
				.must(queryBuilder.keyword().onFields(	"productDesc" + transLcl,
														"productLongDesc" + transLcl, 
														"product.brand.brandDesc" + transLcl,
														"product.categories.categoryDesc" + transLcl,
														"product.categories.parent.categoryDesc" + transLcl,
														"product.categories.parent.parent.categoryDesc" + transLcl, 
														"product.tags.tagDesc" + transLcl
														).matching(searchTerm).createQuery())
				
				.createQuery();

		final FullTextQuery jpaQuery = fullTextEntityManager.createFullTextQuery(searchQuery, ProductEntity.class);

		//initialize the facets
		Set<Facet> facets = new HashSet<Facet>();
		Set<String> codes = new HashSet<String>();

		facetServices.showFacetServices();
		
		facetServices.getFacetServices().stream()
					.forEach(f -> {
						//this will add to facets collection
						this.getDiscreteFacets(lcl, currency, queryBuilder, jpaQuery, f.getFacetCategory(), f.getFacetField(),facets, codes);
					});
		
		
		//get the price facets, which are range facets
		jpaQuery.setSort(getSortField("priceDesc", currency, transLcl));
		List<ProductEntity> results = new ArrayList<ProductEntity>();
		results.addAll(jpaQuery.getResultList());

		if(results.size() > size) {
			this.getPriceFacets(lcl, 
								currency, 
								results, 
								facets, 
								queryBuilder, 
								jpaQuery);
		}
	
		// pull the selected from facetList using the tokens from JSON payload
		Set<Facet> selectedFacets = 
									facetPayload.stream()
									.flatMap(x -> {
										return facets.stream().filter(y -> x.getValue().equals(y.getValue()));
									}).collect(Collectors.toSet());

		// combine the selected facets
		Set<SearchFacetHelper> lsfh = new HashSet<SearchFacetHelper>();
		selectedFacets.stream().forEach(f -> {
			LOGGER.debug(f.getClass().getSimpleName() + " - " + f.getValue() + " - " + f.getCount());

			// apply facets one by one in the order that they are selected
			jpaQuery.getFacetManager().getFacetGroup(f.getFacetingName()).selectFacets(FacetCombine.OR, f);

			// this will not re-fetch from DB
			this.processFacet(lcl, currency, queryBuilder, jpaQuery, facets, f, lsfh);
		});

		// if there are no selected facets the facets set will be initialized with all facets
		if (selectedFacets.isEmpty()) {
			initializeFacetHelpers(lsfh, facets);
		}
		
		// we need to aggregate the codes of each helper of type SimpleFacet
		Set<SearchFacetHelper> aggLsfh = aggregateFacetHelpers(	lsfh
																.stream()
																.filter(f -> f.getType().equals("SimpleFacet"))
																.collect(Collectors.toSet()), 	"SimpleFacet");

		// select the object from DB for each of the aggregated facet helpers
		aggLsfh.stream().forEach(sfh -> {
			
			// create a new array of entity facets
			ISearchDimensionService service = sfh.getBean(appContext);
			
			List<ISearchDimension> lc = service.findAll(lcl, currency, sfh.getCodes());

			facets.stream().filter(x -> !selectedFacets.stream().filter(y -> (x.getValue().equals(y.getValue())))
			.findFirst().isPresent()).collect(Collectors.toSet())
			.stream().filter(f -> sfh.getFacetingName().equals(f.getFacetingName()))
			.forEach(f -> {
				Optional<ISearchDimension> dO = lc.stream().filter(c -> {
							return (c.getCode().equals(service.tokenToCode(f.getValue())));
						}).findFirst();

						if (dO.isPresent()) {
							returnFacets.add(new SearchFacetDiscrete(f, dO.get()));
						}
					});
		});
		
		returnFacets.addAll(facets.stream().filter(x -> !selectedFacets.stream().filter(y -> (x.getValue().equals(y.getValue())))
				.findFirst().isPresent()).collect(Collectors.toSet())
				.stream()
				.filter(f -> f.getClass().getSimpleName().equals("RangeFacetImpl"))
				.map(f -> new SearchFacetRange(f)).collect(Collectors.toSet()));
		
		
		returnFacets.stream()
				.sorted((a, b) -> (a.getType() + a.getValue()).compareTo(b.getFacetingName() + b.getValue()))
				.forEach(f -> {
					LOGGER.debug(f.getType() + " " + f.getDesc() + " -> " + f.getValue() + " -> " + f.getCount()
							+ " - " + f.getFacetingName());
				});

		// set pageable definition for jpaQuery
		Pageable pageable = PageRequest.of(page, size);
		PageableUtil pageableUtil = new PageableUtil();
		jpaQuery.setFirstResult(pageableUtil.getStartPosition(pageable));
		jpaQuery.setMaxResults(pageable.getPageSize());

		// sort the results
		Sort sort = getSortField(sortBy, currency, transLcl);
		jpaQuery.setSort(sort);

		setProductProjection(jpaQuery);
		List<Object[]> result = jpaQuery.getResultList();
		List<String> orderedIds = result.stream().map(o -> o[0].toString()).collect(Collectors.toList());
		List<ProductDTO> lp = productService.findAll( lcl, 
													 currency, 
													 result.stream().map(p -> p[0].toString()).collect(Collectors.toSet())); 
		
		Collections.sort(lp, Comparator.comparing(item -> orderedIds.indexOf(((ProductDTO) item).getProductUPC())));		
	 
		return new PageImpl<ProductDTO>(lp, pageable, jpaQuery.getResultSize());

	}

	@Override
	public String[] getSuggestions(String searchTerm, String locale, String currency) {

		FullTextEntityManager fullTextEntityManager = org.hibernate.search.jpa.Search.getFullTextEntityManager(em);

		QueryBuilder titleQB = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(ProductEntity.class)
				.get();

		Query query = titleQB.phrase().withSlop(2).onField(TITLE_NGRAM_INDEX + cleanLocale(locale))
				.andField(TITLE_EDGE_NGRAM_INDEX + cleanLocale(locale)).boostedTo(5).sentence(searchTerm.toLowerCase())
				.createQuery();

		FullTextQuery jpaQuery = fullTextEntityManager.createFullTextQuery(query, ProductEntity.class);
		jpaQuery.setMaxResults(20);

		setProductProjection(jpaQuery);
		@SuppressWarnings("unchecked")
		List<Object[]> result = jpaQuery.getResultList();
		List<ProductDTO> lp = productService.findAll(locale, currency, result.stream().map(p -> p[0].toString()).collect(Collectors.toSet())); 

		return lp.stream().map(p -> p.getProductDesc()).toArray(String[]::new);

	}

	private void setProductProjection(FullTextQuery jpaQuery) {
		jpaQuery.setProjection("productUPC");							
	}


	private Sort getSortField(String field, String currency, String locale) {
		LOGGER.debug("call getSortField with parameters \"{}\" \"{}\" \"{}\" ", field, currency, locale);
		switch (field) {
			case "nameAsc": 	return new Sort(new SortField(				"productDescSort" 			+ locale, 		SortField.Type.STRING, false));
			case "nameDesc": 	return new Sort(new SortField(				"productDescSort" 			+ locale, 		SortField.Type.STRING, true));
			case "priceAsc": 	return new Sort(new SortedNumericSortField(	"currentMarkdownPrice" 		+ currency, 	SortField.Type.DOUBLE, false));
			case "priceDesc": 	return new Sort(new SortedNumericSortField(	"currentMarkdownPrice" 		+ currency,		SortField.Type.DOUBLE, true));
			case "bestMatch": 	return new Sort(SortField.FIELD_SCORE, new SortField("productDescSort"	+ locale, 		SortField.Type.STRING, true));
			default:			return new Sort(SortField.FIELD_SCORE, new SortField("productDescSort"	+ locale, 		SortField.Type.STRING, true));
		}
	}

}
