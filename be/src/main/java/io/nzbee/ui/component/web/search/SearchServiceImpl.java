package io.nzbee.ui.component.web.search;

import java.math.BigDecimal;
import java.util.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.SortedNumericSortField;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.hibernate.search.query.facet.Facet;
import org.hibernate.search.query.facet.FacetCombine;
import org.hibernate.search.query.facet.FacetSortOrder;
import org.hibernate.search.query.facet.FacetingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.google.common.collect.Lists;

import io.nzbee.SpringContext;
import io.nzbee.domain.IDomainObject;
import io.nzbee.domain.IHierarchicalDomainObject;
import io.nzbee.domain.IService;
import io.nzbee.domain.brand.Brand;
import io.nzbee.domain.brand.IBrandService;
import io.nzbee.domain.category.Category;
import io.nzbee.domain.category.CategoryServiceImpl;
import io.nzbee.domain.category.ICategoryService;
import io.nzbee.domain.product.IProductService;
import io.nzbee.domain.product.Product;
import io.nzbee.domain.tag.ITagService;
import io.nzbee.domain.tag.Tag;
import io.nzbee.entity.PageableUtil;
import io.nzbee.variables.CategoryVars;
import io.nzbee.variables.ProductVars;
import io.nzbee.ui.component.web.facet.IFacetService;
import io.nzbee.ui.component.web.facet.FacetContainer;
import io.nzbee.ui.component.web.facet.FacetHelper;
import io.nzbee.ui.component.web.facet.search.SearchFacet;
import io.nzbee.ui.component.web.generic.UIService;

@Service(value = "SearchService")
@Transactional
@CacheConfig(cacheNames = "products")
public class SearchServiceImpl extends UIService implements ISearchService {

	@Autowired
	@Qualifier("productDomainService")
	private IProductService productService;

	@Autowired
	private ICategoryService categoryService;

	@Autowired
	private IBrandService brandService;
	
	@Autowired
	private ApplicationContext appContext;

	@PersistenceContext(unitName = "mochiEntityManagerFactory")
	private EntityManager em;

	@Override
	public Search findAll(		String locale, 
								String currency, 
								String categoryDesc, 
								String searchTerm, 
								int page,
								int size,
								String sortBy, 
								FacetContainer selectedFacets) {
		
		//IFacet to org.hibernate.search.query.facet.Facet
		//payload to?
		
		
		// call the domain layer service to get a Page of Products
		return this.findAll(
							locale, 
							currency, 
							categoryDesc, 
							searchTerm, 
							page, 
							size, 
							sortBy, 
							selectedFacets.getFacets());
		
	}



	private Set<SearchFacet> processFacets( String locale, 
											String currency,
											QueryBuilder qb,
											org.hibernate.search.jpa.FullTextQuery jpaQuery, 
											Set<SearchFacet> facetList, 
											Set<SearchFacet> selectedFacetList 
											) {
		
		
		Set<String> facetingNames = selectedFacetList.stream()
													 .map(f -> f.getFacetingName())
													 .collect(Collectors.toSet());
		
		//we need a list of unique FacetingName and FieldName
		Set<FacetHelper> lf = facetList.stream()
										.filter(c -> (!facetingNames.contains(c.getFacetingName())))
										.map(f -> {
											FacetHelper sp = new FacetHelper(); 
											sp.setFacetingName(f.getFacetingName());
											sp.setFieldName(f.getFieldName());			
											
											return sp;
										})
										.collect(Collectors.toSet());
		
		
		
		
		//all we need to do is get the distinct getFacetingName, and getFieldName from facetList
		//where the FacetingName is not in selectedFacetList
		return lf.stream()
				 .map(f -> this.getDiscreteFacets(	
							locale,
							currency,
							qb, 
							jpaQuery, 
							f.getFacetingName(), 
							f.getFieldName(),
							f.getBean(appContext)		
							)
			  ).collect(Collectors.toList()).stream()
			   .flatMap(List<SearchFacet>::stream)
			   .collect(Collectors.toSet());
		
	}
	

	private  List<SearchFacet> getDiscreteFacets(	   String locale, 
													   String currency, 
													   QueryBuilder qb, 
													   org.hibernate.search.jpa.FullTextQuery jpaQuery,
													   String facetingName, 
													   String fieldReference,
													   IService service) {
		
		// create a category faceting request for the base level
		FacetingRequest facetRequest = qb.facet().name(facetingName).onField(fieldReference) // in category class
				.discrete().orderedBy(FacetSortOrder.COUNT_DESC).includeZeroCounts(false).createFacetingRequest();

		// add all the base level facets to categoryFacets List
		jpaQuery.getFacetManager().enableFaceting(facetRequest);
		
		//Get all the id's of the facets in one go
		final List<Facet> facets = jpaQuery.getFacetManager().getFacets(facetingName);
		
		Set<String> uniqueCodes = new HashSet<String>(), uniqueFieldRefs = new HashSet<String>();
		
		//Add all the category codes up the hierarchy
		facets.stream().forEach(f -> {
			List<String> codes = Arrays.asList(f.getValue().split("/")).stream().filter(o -> !o.isEmpty()).collect(Collectors.toList());
			
			uniqueCodes.addAll(codes);
			
			//if codes array length is > 1 then the facet is hierarchical
			if(codes.size() > 1) {
				getFieldRefs(f, codes, uniqueFieldRefs);
			}
			
		});
		
		uniqueFieldRefs.stream().forEach(fr -> {
			System.out.println(fr);
		});
		
		uniqueFieldRefs.stream().forEach(fr -> {
			FacetingRequest frq = qb.facet().name(facetingName).onField(fr) // in category class
					.discrete().orderedBy(FacetSortOrder.COUNT_DESC).includeZeroCounts(false).createFacetingRequest();
			
			jpaQuery.getFacetManager().enableFaceting(frq);
			facets.addAll(jpaQuery.getFacetManager().getFacets(facetingName));
		});
		
		
		//query the domain objects from the DB
		@SuppressWarnings("unchecked")
		List<IDomainObject> lc = service.findAll(locale, currency, new ArrayList<String>(uniqueCodes));

		//create a new array of entity facets
		List<SearchFacet> lef = new ArrayList<SearchFacet>(uniqueCodes.size());
		
		facets.stream().forEach(f -> {
				Optional<IDomainObject> dO = lc.stream()
											  .filter(c -> c.getCode().equals(service.tokenToCode(f.getValue())))
											  .findFirst();
						
				if(dO.isPresent()) {
					lef.add(new SearchFacet(f, dO.get()));
				}
		});
		
		//get the object array for the ids in previous step
		return lef;
	}
	
	
	private void getFieldRefs(Facet f, List<String> codes, final Set<String> fieldRefs) { 
		//we need to use a standard loop to extract a range
		for (int i=0; i < codes.size(); i++) {
				//we need to store this information in something like an enum
				//parse this to a token to fetch and use the ordinal to retrieve the Lucene fieldReference
				List<String> ls = codes.subList(0, i+1).stream().filter(o -> !(o.isEmpty())).collect(Collectors.toList());
				String prefix = f.getFieldName().split("\\.")[0];
				String suffix = f.getFieldName().split("\\.")[f.getFieldName().split("\\.").length-1];
				int numParents = codes.size() - ls.size();
				
				String newToken = "/" + String.join("/", ls);
				String newFieldReference = prefix +  StringUtils.repeat(".parent", numParents) + "." + suffix;
				
				fieldRefs.add(newFieldReference);
		}
	}

	/*
	{
{
	"facets": [],
	"brands": [
		{
       "@class": "io.nzbee.ui.component.web.facet.search.SearchFacet",
                "value": "/PRM01/FRT01/POM01",
                "id": "POM01",
                "type": "SearchFacet",
                "payload": {
                    "@class": "io.nzbee.domain.category.ProductCategory",
                    "categoryType": "product",
                    "parentCode": "FRT01",
                    "layoutCodes": [],
                    "level": 2,
                    "desc": "Pomes",
                    "code": "POM01",
                    "hierarchical": true,
                    "count": 3,
                    "childCount": 0
                },
                "count": 3,
                "fieldName": "primaryCategory.categoryToken",
                "payloadType": "ProductCategory",
                "facetingName": "CategoryFR",
                "hierarchical": true,
                "displayValue": "Pomes (3)",
                "facetQuery": {
                    "boost": 1,
                    "term": {}
                }
            }
       
       
        ]

}
	 */
	
	@SuppressWarnings("unchecked")
	@Override
	// @Cacheable
	public Search findAll(String lcl, 
						  String currency, 
						  String categoryDesc, 
						  String searchTerm, 
						  int page, 
						  int size,
						  String sortBy, 
						  List<io.nzbee.ui.component.web.facet.IFacet> facetPayload) {
		
		FullTextEntityManager fullTextEntityManager = org.hibernate.search.jpa.Search.getFullTextEntityManager(em);

		String transLcl = lcl.substring(0, 2).toUpperCase() + lcl.substring(3, 5).toUpperCase();

		QueryBuilder productQueryBuilder = fullTextEntityManager.getSearchFactory().buildQueryBuilder()
				.forEntity(io.nzbee.entity.product.attribute.ProductAttribute.class)
				.overridesForField("productDesc", lcl)
				.overridesForField("brandDesc", lcl)
				.overridesForField("tagA", lcl)
				.overridesForField("tagB", lcl)
				.overridesForField("tagC", lcl)
				.get();

		// this is a Lucene query using the Lucene api
		org.apache.lucene.search.Query searchQuery = productQueryBuilder.bool().must(productQueryBuilder.keyword()
				.onFields(
						"primaryCategory.parent.parent.parent." + "primaryCategoryDesc" + transLcl,
						"primaryCategory.parent.parent." + "primaryCategoryDesc" + transLcl,
						"primaryCategory.parent." + "primaryCategoryDesc" + transLcl,
						"primaryCategory.primaryCategoryDesc" + transLcl,
						"secondaryCategory.parent.parent.parent." + "secondaryCategoryDesc" + transLcl,
						"secondaryCategory.parent.parent." + "secondaryCategoryDesc" + transLcl,
						"secondaryCategory.parent." + "secondaryCategoryDesc" + transLcl,
						"secondaryCategory." + "secondaryCategoryDesc" + transLcl, 
						"brandDescForIndex",
						"productDesc", 
						"tagA",
						"tagB", 
						"tagC"
						)
				.matching(searchTerm).createQuery())
				.must(productQueryBuilder.keyword().onFields("lclCd").matching(lcl).createQuery()).createQuery();

		org.hibernate.search.jpa.FullTextQuery jpaQuery = fullTextEntityManager.createFullTextQuery(searchQuery,
				io.nzbee.entity.product.attribute.ProductAttribute.class);

		final Set<SearchFacet> facetList = new HashSet<SearchFacet>();

		// initialize the facets
		facetList.addAll( this.getDiscreteFacets(lcl,
												 currency,
												 productQueryBuilder, 
												 jpaQuery, 
												 CategoryVars.PRIMARY_CATEGORY_FACET_NAME,
												 "primaryCategory.categoryToken",
												 categoryService));
		
		facetList.addAll( this.getDiscreteFacets(lcl,
												 currency,
												 productQueryBuilder, 
												 jpaQuery, 
												 CategoryVars.BRAND_FACET_NAME,
												 "brandCode",
												 brandService));
		
		
		//get the list of tokens from the selected facets passed as parameter
		List<String> sft = facetPayload.stream().map(f -> f.getValue()).collect(Collectors.toList());
		
		//pull the selected from facetList using the tokens from JSON payload
		Set<SearchFacet> selectedFacets = sft.stream().flatMap(x -> {
														return facetList.stream()
																.filter(y -> x.equals(y.getValue()));
						  							}).collect(Collectors.toSet());
		
		//combine the selected facets
		selectedFacets.stream().forEach(f -> {
			//System.out.println(f.getPayload().getClass().getSimpleName() + " - " + f.getValue() + " - " + f.getCount());
			jpaQuery.getFacetManager().getFacetGroup(f.getFacetingName()).selectFacets(FacetCombine.OR, f);
		});
		
		//re-process the facets
		Set<SearchFacet> processedFacets = this.processFacets(lcl, 
															   currency, 
															   productQueryBuilder, 
															   jpaQuery, 
															   facetList, 
															   selectedFacets
												   );
		
		
		
		processedFacets.stream().forEach(f -> {
			System.out.println(f.getPayload().getClass().getSimpleName() + " - " + f.getValue() + " - " + f.getCount());
		});
		
		
		//we need to combine the passed facets, then reprocess them
		//jpaQuery.getFacetManager().getFacetGroup(f.getFacetingName()).selectFacets(FacetCombine.OR, f);
		
		
//		
		
//		allFacets.addAll(this.getDiscreteFacets(productQueryBuilder, jpaQuery, CategoryVars.PRIMARY_CATEGORY_FACET_NAME,
//				"secondaryCategory.categoryToken"));
//		allFacets.addAll(
//				this.getDiscreteFacets(productQueryBuilder, jpaQuery, CategoryVars.BRAND_FACET_NAME, "brandCode"));
//		allFacets.addAll(this.getRangeFacets(productQueryBuilder, jpaQuery, currency));
//		allFacets.addAll(
//				this.getDiscreteFacets(productQueryBuilder, jpaQuery, CategoryVars.TAG_FACET_NAME, "tagAFacet"));
//		allFacets.addAll(
//				this.getDiscreteFacets(productQueryBuilder, jpaQuery, CategoryVars.TAG_FACET_NAME, "tagBFacet"));
//		allFacets.addAll(
//				this.getDiscreteFacets(productQueryBuilder, jpaQuery, CategoryVars.TAG_FACET_NAME, "tagCFacet"));
//
//		allFacets.addAll(allFacets.stream()
//				.filter(f -> f.getFacetingName().equals(CategoryVars.PRIMARY_CATEGORY_FACET_NAME)).map(f -> {
//					return getParentCategoryFacets(new HashSet<Facet>(), f, productQueryBuilder, jpaQuery, lcl,
//							currency);
//				}).collect(Collectors.toSet()).stream().flatMap(Set::stream).collect(Collectors.toSet()));
//		
//		List<String> allTokens = new ArrayList<String>();
//		allTokens.addAll(categoryTokens);
//		allTokens.addAll(brandTokens);
//		allTokens.addAll(tagTokens);
//		allTokens.addAll(priceTokens);

		// filter to get the facets that are selected
//		lf = allTokens.stream().flatMap(x -> {
//			return allFacets.stream().filter(y -> x.equals(y.getValue()));
//		}).collect(Collectors.toList());
//
//		
//		cs = new HashSet<NavFacet<Category>>();
//		lf.stream().forEach(f -> {
//			jpaQuery.getFacetManager().getFacetGroup(f.getFacetingName()).selectFacets(FacetCombine.OR, f);
//			processFacets(allFacets, productQueryBuilder, jpaQuery, currency, f.getFacetingName());
//			if (f.getFacetingName().equals(CategoryVars.PRIMARY_CATEGORY_FACET_NAME)) {
//				allFacets.addAll(
//						getParentCategoryFacets(new HashSet<Facet>(), f, productQueryBuilder, jpaQuery, lcl, currency));
//			}
//		});
//		
//	
//		lf = allTokens.stream().flatMap(x -> {
//			return allFacets.stream().filter(y -> x.equals(y.getValue()));
//		}).collect(Collectors.toList());
//
//		lf.stream().forEach(f -> {
//			jpaQuery.getFacetManager().getFacetGroup(f.getFacetingName()).selectFacets(FacetCombine.OR, f);
//		});
//
//		allFacets.stream().filter(f -> f.getFacetingName().equals(CategoryVars.PRIMARY_CATEGORY_FACET_NAME))
//				.collect(Collectors.toList()).stream().forEach(cf -> {
//					
//					String categoryCode = (new LinkedList<String>(Arrays.asList(cf.getValue().split("/")))).getLast();
//					Optional<Category> category = categoryService.findByCode(lcl, currency, categoryCode);
////					System.out.println(category.getCategoryDesc());
////					System.out.println(category.getCategoryType());
////					System.out.println(category.getClass().getSimpleName());
//					NavFacet<Category> categoryFacet = facetService.convertCatToNavFacet(category.get());
//					categoryFacet.setFacetProductCount(new Long(cf.getCount()));
//					categoryFacet.setToken(cf.getValue());
//					categoryFacet.setFacetType(ProductVars.FACET_TYPE_DISCRETE);
//					cs.add(categoryFacet);
//				});

//	
//		
//		bs = new HashSet<NavFacet<Brand>>();
//		allFacets.stream().filter(f -> f.getFacetingName().equals(CategoryVars.BRAND_FACET_NAME))
//				.collect(Collectors.toList()).forEach(bf -> {
//					Brand brand = brandService.findByCode(lcl, currency, bf.getValue()).get();
//					NavFacet<Brand> brandFacet = facetService.convertBrandToNavFacet(brand);
//					brandFacet.setFacetProductCount(new Long(bf.getCount()));
//					brandFacet.setToken(bf.getValue());
//					brandFacet.setFacetType(ProductVars.FACET_TYPE_DISCRETE);
//					bs.add(brandFacet);
//				});
//
//		// for each of the baseline facets, convert them to Facet DTOs for the client
//		// and add them to "s"
//		final List<NavFacet<Object>> ps = new ArrayList<NavFacet<Object>>();
//		allFacets.stream().filter(f -> f.getFacetingName().equals(CategoryVars.PRICE_FACET_NAME))
//				.collect(Collectors.toList()).forEach(pf -> {
//					NavFacet<Object> priceFacet = new NavFacet<Object>();
//					priceFacet.setFacetClassName("Product.productMarkdown");
//					priceFacet.setFacetId(facetService.calcFacetId(priceFacet.getFacetClassName(), pf.getValue()));
//					priceFacet.setFacetDisplayValue(pf.getValue());
//					priceFacet.setFacetProductCount(new Long(pf.getCount()));
//					priceFacet.setToken(pf.getValue());
//					priceFacet.setFacetType(ProductVars.FACET_TYPE_RANGE);
//					ps.add(priceFacet);
//				});
//
//		final List<NavFacet<Tag>> ts = new ArrayList<NavFacet<Tag>>();
//		allFacets.stream().filter(f -> f.getFacetingName().equals(CategoryVars.TAG_FACET_NAME))
//				.collect(Collectors.toList()).forEach(tf -> {
//					if(tf.getValue().equals("Empty"))  return; 
//					Tag tag = tagService.findByDesc(lcl, currency, tf.getValue()).get();
//					NavFacet<Tag> tagFacet = facetService.convertTagToNavFacet(tag);
//					tagFacet.setFacetProductCount(new Long(tf.getCount()));
//					tagFacet.setToken(tf.getValue());
//					tagFacet.setFacetType(ProductVars.FACET_TYPE_DISCRETE);
//					ts.add(tagFacet);
//				});
//
//		List<NavFacet<?>> returnFacets = new ArrayList<NavFacet<?>>();
//		returnFacets.addAll(cs);
//		returnFacets.addAll(bs);
//		returnFacets.addAll(ts);
//		returnFacets.addAll(ps);
//		
//		returnFacets.stream().forEach(f -> {
//			System.out.println(f.getFacetDisplayValue() + " " + f.getFacetClassName());
//		});

		// set pageable definition for jpaQuery
		Pageable pageable = PageRequest.of(page, size);
		PageableUtil pageableUtil = new PageableUtil();
		jpaQuery.setFirstResult(pageableUtil.getStartPosition(pageable));
		jpaQuery.setMaxResults(pageable.getPageSize());

		// sort the results
		org.apache.lucene.search.Sort sort = getSortField(sortBy, currency);
		jpaQuery.setSort(sort);
		
		jpaQuery.setProjection("Id", 
							   "productId", 
							   "productDesc", 
							   "ProductImage",
							   "lclCd",
							   "product.productUPC",
							   "product.productCreateDt",
							   "product.brand.brandCode",
							   "brandDescForIndex",
							   "product.currentRetailPrice"		+ currency,
							   "product.currentMarkdownPrice" 	+ currency);

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
			return p;
		}).collect(Collectors.toList());

		Search search = new Search(); 
		search.setProducts(new PageImpl<Product>(lp.stream().map(p->productService.dtoToDO(p))
				.collect(Collectors.toList()), pageable, jpaQuery.getResultSize()));
		
		
		FacetContainer nfc = new FacetContainer();
		nfc.setFacets(facetList.stream().collect(Collectors.toList()));
		search.setFacets(nfc);
		return search;
	}

//	private Set<Facet> getParentCategoryFacets(Set<Facet> cfs, Facet sf, QueryBuilder qb,
//			org.hibernate.search.jpa.FullTextQuery q, String locale, String currency) {
//		if (sf == null) {
//			return cfs;
//		}
//
//		String categoryCode = (new LinkedList<String>(Arrays.asList(sf.getValue().split("/")))).getLast();
//
//		Optional<Category> c = categoryService.findByCode(locale, currency, categoryCode);
//		if (!c.isPresent()) {
//			return cfs;
//		}
//		if (c.get().getParentCode() == null) {
//			return cfs;
//		}
//		
//		Optional<Category> oParent = categoryService.findByCode(locale, currency, c.get().getParentCode());
//		
//		if (!oParent.isPresent()) {
//			return cfs;
//		}
//		
//		Category parent = oParent.get();
//
//		// if we hit the root node, there are no parents
//		if (parent.getCategoryCode().equals(CategoryVars.PRIMARY_HIERARCHY_ROOT_CODE)) {
//			return cfs;
//		}
//		
//		Long parentLevel = parent.getCategoryLevel();
//		
//		String frName = sf.getFacetingName();
//		String frField = sf.getFieldName().split("\\.")[0]
//				+ StringUtils.repeat(".parent", c.get().getCategoryLevel().intValue() - parentLevel.intValue())
//				+ ".categoryToken";
//
//		Optional<Facet> oParentFacet = this.getDiscreteFacets(qb, q, frName, frField).stream()
//				.filter(f -> f.getValue().equals(sf.getValue().replace("/" + categoryCode, ""))).findFirst();
//		
//		if (!oParentFacet.isPresent()) {
//			return cfs;	
//		} 
//		
//		Facet parentFacet = oParentFacet.get();
//		
//		cfs.add(parentFacet);
//		
//		return this.getParentCategoryFacets(cfs, parentFacet, qb, q, locale, currency);
//	}

//	@SuppressWarnings("unchecked")
//	private List<Facet> getRangeFacets(QueryBuilder qb, org.hibernate.search.jpa.FullTextQuery jpaQuery,
//			String currency) {
//
//		org.apache.lucene.search.Sort sort = getSortField("priceDesc", currency);
//		jpaQuery.setSort(sort);
//
//		List<io.nzbee.entity.product.attribute.ProductAttribute> results = jpaQuery.getResultList();
//
//		if (results.isEmpty()) {
//			return new ArrayList<Facet>();
//		}
//
//		Double maxPrice = results.stream().findFirst().get().getProduct().getCurrentMarkdownPriceHKD();
//		Double minPrice = Lists.reverse(results).stream().findFirst().get().getProduct().getCurrentMarkdownPriceHKD();
//		Double inc = (maxPrice > 0) ? (maxPrice - ((minPrice.equals(maxPrice)) ? 0 : minPrice)) / 4 : maxPrice;
//
//		inc = new BigDecimal(inc).setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
//
//		Double below = inc,
//				froma = (new BigDecimal(inc + new Double(0.01)).setScale(2, BigDecimal.ROUND_DOWN).doubleValue()),
//				toa = (new BigDecimal(inc * 2).setScale(2, BigDecimal.ROUND_DOWN).doubleValue()),
//				fromb = (new BigDecimal(toa + new Double(0.01)).setScale(2, BigDecimal.ROUND_DOWN).doubleValue()),
//				tob = (new BigDecimal(inc * 4).setScale(2, BigDecimal.ROUND_DOWN).doubleValue()), above = tob;
//
//		FacetingRequest facetRequest = qb.facet().name(CategoryVars.PRICE_FACET_NAME)
//				.onField("product.currentMarkdownPrice" + currency + "Facet") // In product class
//				.range().below(below).from(froma).to(toa).from(fromb).to(tob).above(above)
//				.orderedBy(FacetSortOrder.RANGE_DEFINITION_ORDER).createFacetingRequest();
//
//		jpaQuery.getFacetManager().enableFaceting(facetRequest);
//		return jpaQuery.getFacetManager().getFacets(CategoryVars.PRICE_FACET_NAME);
//	}


	private org.apache.lucene.search.Sort getSortField(String field, String currency) {
		switch (field) {
		case "nameAsc":
			return new org.apache.lucene.search.Sort(
					new SortField(getSortFieldName(field, currency), SortField.Type.STRING, false));
		case "nameDesc":
			return new org.apache.lucene.search.Sort(
					new SortField(getSortFieldName(field, currency), SortField.Type.STRING, true));
		case "priceAsc":
			return new org.apache.lucene.search.Sort(
					new SortedNumericSortField(getSortFieldName(field, currency), SortField.Type.DOUBLE, false));
		case "priceDesc":
			return new org.apache.lucene.search.Sort(
					new SortedNumericSortField(getSortFieldName(field, currency), SortField.Type.DOUBLE, true));
		default:
			return new org.apache.lucene.search.Sort(
					new SortField(getSortFieldName(field, currency), SortField.Type.STRING, true));
		}
	}

	private String getSortFieldName(String field, String currency) {
		switch (field) {
		case "nameAsc":
			return "productDesc";
		case "nameDesc":
			return "productDesc";
		case "priceDesc":
			return "product.currentMarkdownPrice" + currency;
		case "priceAsc":
			return "product.currentMarkdownPrice" + currency;
		default:
			return "productDesc";
		}
	}



}
