package io.nzbee.ui.component.web.search;

import java.util.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.SortedNumericSortField;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.hibernate.search.query.facet.Facet;
import org.hibernate.search.query.facet.FacetCombine;
import org.hibernate.search.query.facet.FacetSortOrder;
import org.hibernate.search.query.facet.FacetingRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import io.nzbee.domain.FacetServices;
import io.nzbee.domain.IDomainObject;
import io.nzbee.domain.IService;
import io.nzbee.domain.product.IProductService;
import io.nzbee.domain.product.Product;
import io.nzbee.entity.PageableUtil;
import io.nzbee.ui.component.web.facet.search.SearchFacet;
import io.nzbee.ui.component.web.facet.search.SearchFacetHelper;
import io.nzbee.ui.component.web.facet.search.SearchFacetWithFieldHelper;
import io.nzbee.ui.component.web.generic.UIService;

@Service(value = "SearchService")
@CacheConfig(cacheNames = "products")
public class SearchServiceImpl extends UIService implements ISearchService {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	@Qualifier("productDomainService")
	private IProductService productService;
	
	@Autowired
	private ApplicationContext appContext;
	
	@Autowired
	private FacetServices facetServices;

	@PersistenceContext(unitName = "mochiEntityManagerFactory")
	private EntityManager em;

	private Set<Facet> processFacet( String locale, 
											String currency,
											QueryBuilder qb,
											org.hibernate.search.jpa.FullTextQuery jpaQuery, 
											Set<Facet> facets,
											Facet selectedFacet,
											Set<SearchFacetHelper> setSearchFacetHelper) {
		
		//we need a list of unique FacetingName and FieldName, use facets Set to create the helpers
		Set<SearchFacetWithFieldHelper> lf = facets
													.stream()
													.map(f -> {
																SearchFacetWithFieldHelper sp = new SearchFacetWithFieldHelper(); 
																sp.setFacetingName(f.getFacetingName());
																sp.setFieldName(f.getFieldName());			
																return sp;
													})
													.collect(Collectors.toSet());
		
		facets.removeAll(facets
								.stream()
								.filter(f -> !selectedFacet.getFacetingName().contains(f.getFacetingName()))
								.collect(Collectors.toSet()));
		
		
		
		lf.stream()
		  .map(f -> f.getFacetingName()).collect(Collectors.toSet())
		  .stream().forEach(s -> {
			  
			  Set<String> ss = new HashSet<String>();
			  SearchFacetHelper sfh = new SearchFacetHelper();
			  
			  lf.stream()
			  .filter(f -> f.getFacetingName().equals(s))
			  .forEach(f -> {
				  	this.getDiscreteFacets(	locale,
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
			  setSearchFacetHelper.add(sfh);
		  });
	
		return facets;
	}
	
	private Set<SearchFacetHelper> aggregateFacetHelpers(Set<SearchFacetHelper> lsfh) {
		Set<SearchFacetHelper> newLsfh = new HashSet<SearchFacetHelper>();
		lsfh.stream().map(sfh -> sfh.getFacetingName()).collect(Collectors.toSet())
					   .stream()
					   .forEach(f -> {
						   Set<String> sstr = new HashSet<String>(); 
						   lsfh.stream().filter(fh -> fh.getFacetingName().equals(f))
						   				.collect(Collectors.toSet())
						   				.stream()
						   				.forEach(l -> {sstr.addAll(l.getCodes());});
						   
						   SearchFacetHelper nsfh = new SearchFacetHelper();
						   nsfh.setFacetingName(f);
						   nsfh.setCodes(sstr);
						   newLsfh.add(nsfh);
					   });
		return newLsfh;
	}
	
	private Set<SearchFacetHelper> initializeFacetHelpers(Set<SearchFacetHelper> lsfh, Set<Facet> facets) {
		//check if we actually have any selected facets
		//if not we need to initialize the array lsfh
		
		facets.stream().map(f -> f.getFacetingName()).collect(Collectors.toSet())
						   .stream()
						   .forEach(str -> {
							   				SearchFacetHelper sfh = new SearchFacetHelper();
							   				sfh.setFacetingName(str);
							   				lsfh.add(sfh);
						   				  });
		
		return lsfh;
	}
	
	private  Set<String> getDiscreteFacets(	   String locale, 
											   String currency, 
											   QueryBuilder qb, 
											   org.hibernate.search.jpa.FullTextQuery jpaQuery,
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
		
		//Get all the id's of the facets in one go
		facets.addAll(jpaQuery.getFacetManager().getFacets(facetingName));
		
		Set<String> uniqueCodes = new HashSet<String>();
		Set<String>	uniqueFieldRefs = new HashSet<String>();
		
		//Add all the category codes up the hierarchy
		facets.stream()
			  .filter(f -> f.getFacetingName().equals(facetingName))
			  .forEach(f -> {
					//this is concatenating both brand and category codes
					List<String> codes = Arrays.asList(f.getValue().split("/")).stream().filter(o -> !o.isEmpty()).collect(Collectors.toList());
					
					uniqueCodes.addAll(codes);
					
					//if codes array length is > 1 then the facet is hierarchical
					if(codes.size() > 1) {
						getFieldRefs(f, codes, uniqueFieldRefs);
					}
			
		});
		
		ss.addAll(uniqueCodes);
		
		uniqueFieldRefs.stream().forEach(fr -> {
			FacetingRequest frq = qb.facet().name(facetingName)
											.onField(fr) 
											.discrete()
											.orderedBy(FacetSortOrder.COUNT_DESC)
											.includeZeroCounts(false)
											.createFacetingRequest();
			
			jpaQuery.getFacetManager().enableFaceting(frq);
			facets.addAll(jpaQuery.getFacetManager().getFacets(facetingName));
		});
				
		//get the object array for the ids in previous step
		return ss;
	}

	private void getFieldRefs(Facet f, List<String> codes, final Set<String> fieldRefs) { 
		//we need to use a standard loop to extract a range
		for (int i=0; i < codes.size(); i++) {
				//we need to store this information in something like an enum
				//parse this to a token to fetch and use the ordinal to retrieve the Lucene fieldReference
				List<String> ls = codes.subList(0, i+1).stream().filter(o -> !(o.isEmpty())).collect(Collectors.toList());
				String prefix = f.getFieldName().split("\\.")[0] + "." + f.getFieldName().split("\\.")[1];
				String suffix = f.getFieldName().split("\\.")[f.getFieldName().split("\\.").length-1];
				int numParents = codes.size() - ls.size();
				
				String newFieldReference = prefix +  StringUtils.repeat(".parent", numParents) + "." + suffix;
				
				fieldRefs.add(newFieldReference);
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	// @Cacheable
	public Page<Product> findAll(String lcl, 
						  String currency, 
						  String categoryDesc, 
						  String searchTerm, 
						  int page, 
						  int size,
						  String sortBy, 
						  Set<io.nzbee.ui.component.web.facet.IFacet> facetPayload,
						  Set<io.nzbee.ui.component.web.facet.IFacet> returnFacets) {
		
		FullTextEntityManager fullTextEntityManager = org.hibernate.search.jpa.Search.getFullTextEntityManager(em);

		String transLcl = lcl.substring(0, 2).toUpperCase() + lcl.substring(3, 5).toUpperCase();
		
		QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory().buildQueryBuilder()
				.forEntity(io.nzbee.entity.product.Product.class)
				.overridesForField("productDesc", lcl)
				.overridesForField("product.brand.brandDesc", lcl)
				.overridesForField("product.categories.categoryDesc", lcl)
				.overridesForField("product.categories.parent.categoryDesc", lcl)
				.overridesForField("product.categories.parent.parent.categoryDesc", lcl)
				.overridesForField("product.tags.tagDesc", lcl)
				.get();

		// this is a Lucene query using the Lucene api
		org.apache.lucene.search.Query searchQuery = queryBuilder.bool().must(queryBuilder.keyword()
				.onFields(
						"productDesc" + transLcl,
						"product.brand.brandDesc" + transLcl,
						"product.categories.categoryDesc" + transLcl,
						"product.categories.parent.categoryDesc" + transLcl, 
						"product.categories.parent.parent.categoryDesc" + transLcl,
						"product.tags.tagDesc" + transLcl
						)
				.matching(searchTerm)
				.createQuery()).createQuery();

		final org.hibernate.search.jpa.FullTextQuery jpaQuery = fullTextEntityManager.createFullTextQuery(searchQuery,
				io.nzbee.entity.product.Product.class);
		
		// initialize the facets
		Set<Facet> facets = new HashSet<Facet>();
		Set<String> codes = new HashSet<String>();
		
		facetServices.showFacetServices();
		
		facetServices
			.getFacetServices()
			.stream()
			.forEach(f -> {
			this.getDiscreteFacets(lcl,
								   currency,
								   queryBuilder, 
								   jpaQuery, 
								   f.getFacetCategory(),
								   f.getFacetField(),
								   //facets will contain a fully initialized list of facets
								   facets,
								   codes);
		});
		
		//pull the selected from facetList using the tokens from JSON payload
		Set<Facet> selectedFacets = facetPayload.stream().flatMap(x -> {
														return facets.stream()
																.filter(y -> x.getValue().equals(y.getValue()));
						  							}).collect(Collectors.toSet());
		
		//combine the selected facets
		Set<SearchFacetHelper> lsfh = new HashSet<SearchFacetHelper>();
		selectedFacets.stream()
					  .forEach(f -> {
						LOGGER.debug(f.getClass().getSimpleName() + " - " + f.getValue() + " - " + f.getCount());
						
						//apply facets one by on in the order that they are selected
						jpaQuery.getFacetManager().getFacetGroup(f.getFacetingName()).selectFacets(FacetCombine.OR, f);
						
						//this will not refetch from DB
						this.processFacet( 	lcl, 
											currency, 
											queryBuilder, 
											jpaQuery, 
											facets,
											f,
											lsfh);
		});
		
		//if there are no selected facets the facets set will be initialized with all facets
		if(selectedFacets.isEmpty()) {
			initializeFacetHelpers(lsfh, facets);
		}
		
		//we need to aggregate the codes of each helper 
		Set<SearchFacetHelper> aggLsfh = aggregateFacetHelpers(lsfh);
		
		//select the domain object from DB for each of the aggregated facet helpers
		aggLsfh.stream().forEach(sfh -> {
			Set<IDomainObject> lc = sfh.getBean(appContext).findAll(lcl, currency, new ArrayList<String>(sfh.getCodes()));

			//create a new array of entity facets
			IService service = sfh.getBean(appContext);
			
			facets.stream()
			      .filter(x -> {
									return !selectedFacets.stream()
											.filter(y -> (x.getValue().equals(y.getValue())))
											.findFirst().isPresent();
								}).collect(Collectors.toSet())
			      .stream()
				  .filter(f -> sfh.getFacetingName().equals(f.getFacetingName()))
				  
				  .forEach(f -> {
					  
					  Optional<IDomainObject> dO = lc.stream()
											  		 .filter(c -> {
												  		return (c.getCode().equals(service.tokenToCode(f.getValue())));
											  		  })
											  		 .findFirst();
							
					  if(dO.isPresent()) {
						  returnFacets.add(new SearchFacet(f, dO.get()));
					  }
			});
		});
		
		returnFacets.stream()
		.sorted( (a, b) -> (a.getPayloadType() + a.getValue()).compareTo(b.getPayloadType() + b.getValue()))
		.forEach(f -> {
			LOGGER.debug(f.getPayloadType() + " " + 
						 f.getValue() + " -> " +  
						 f.getDisplayValue() + " -> " + 
						 f.getCount() + " - " +  
						 f.getFacetingName()
						 
						 
						 
			);
		});

		
		// set pageable definition for jpaQuery
		Pageable pageable = PageRequest.of(page, size);
		PageableUtil pageableUtil = new PageableUtil();
		jpaQuery.setFirstResult(pageableUtil.getStartPosition(pageable));
		jpaQuery.setMaxResults(pageable.getPageSize());

		// sort the results
		org.apache.lucene.search.Sort sort = getSortField(sortBy, currency, transLcl);
		jpaQuery.setSort(sort);
		
		jpaQuery.setProjection("Id", 
							   "productId", 
							   "productDesc" + transLcl, 
							   "productImage" + transLcl,
							   "lclCd",
							   "productUPC",
							   "productCreateDt",
							   "product.brand.brandCode",
							   "brandDescForIndex",
							   "currentRetailPrice" + currency,
							   "currentMarkdownPrice" + currency,
							   "displayCategories" + transLcl);

		// get the results using jpaQuery object
		List<Object[]> results = jpaQuery.getResultList();
		
		// convert the results of jpaQuery to product Data Transfer Objects			
		List<io.nzbee.dto.product.Product> lp = results.stream().map(r -> {
			io.nzbee.dto.product.Product p = new io.nzbee.dto.product.Product();
			p.setProductDesc(r[2].toString());
			p.setProductImage(r[3].toString());
			p.setLclCd(lcl);
			p.setProductUPC(r[5].toString());
			p.setProductCreateDt((Date) r[6]);
			p.setProductRetail(Double.parseDouble(r[9].toString()));
			p.setProductMarkdown(Double.parseDouble(r[10].toString()));
			p.setCurrency(currency);
			p.setDisplayCategories(r[11].toString());
			return p;
		}).collect(Collectors.toList());
		
		return new PageImpl<Product>(lp.stream().map(p->productService.dtoToDO(Optional.ofNullable(p)).get())
				.collect(Collectors.toList()), pageable, jpaQuery.getResultSize());
		
	}


	private org.apache.lucene.search.Sort getSortField(String field, String currency, String locale) {
		switch (field) {
		case "nameAsc":
			return new org.apache.lucene.search.Sort(
					new SortField(getSortFieldName(field, currency, locale), SortField.Type.STRING, false));
		case "nameDesc":
			return new org.apache.lucene.search.Sort(
					new SortField(getSortFieldName(field, currency, locale), SortField.Type.STRING, true));
		case "priceAsc":
			return new org.apache.lucene.search.Sort(
					new SortedNumericSortField(getSortFieldName(field, currency, locale), SortField.Type.DOUBLE, false));
		case "priceDesc":
			return new org.apache.lucene.search.Sort(
					new SortedNumericSortField(getSortFieldName(field, currency, locale), SortField.Type.DOUBLE, true));
		default:
			return new org.apache.lucene.search.Sort(
					new SortField(getSortFieldName(field, currency, locale), SortField.Type.STRING, true));
		}
	}

	private String getSortFieldName(String field, String currency, String locale) {
		switch (field) {
		case "nameAsc":
			return "productDescSort" + locale;
		case "nameDesc":
			return "productDescSort" + locale;
		case "priceDesc":
			return "product.currentMarkdownPrice" + currency;
		case "priceAsc":
			return "product.currentMarkdownPrice" + currency;
		default:
			return "productDescSort" + locale;
		}
	}



}
