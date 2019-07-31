package io.nzbee.ui.component.web.search;

import java.math.BigDecimal;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.google.common.collect.Lists;
import io.nzbee.domain.Brand;
import io.nzbee.domain.Category;
import io.nzbee.domain.Product;
import io.nzbee.domain.Tag;
import io.nzbee.domain.services.category.ICategoryService;
import io.nzbee.domain.services.product.IProductService;
import io.nzbee.entity.PageableUtil;
import io.nzbee.domain.services.brand.IBrandService;
import io.nzbee.entity.product.attribute.ProductAttribute;
import io.nzbee.variables.CategoryVars;
import io.nzbee.variables.ProductVars;
import io.nzbee.ui.component.web.facet.NavFacet;
import io.nzbee.ui.component.web.generic.UIService;

@Service(value = "SearchService")
@Transactional
@CacheConfig(cacheNames="products")
public class SearchServiceImpl extends UIService implements ISearchService {

	@Autowired
	@Qualifier("productDomainService")
	private IProductService productService;
	
	@Autowired
	private ICategoryService categoryService;

	@Autowired
	private IBrandService brandService;
	
	@PersistenceContext(unitName = "mochiEntityManagerFactory")
	private EntityManager em;
	
	@Override
	public Search findAll(String locale, 
			 String currency, 
			 String categoryDesc, 
			 String searchTerm, 
			 int page, 
			 int size, 
			 String sortBy, 
			 List<NavFacet> selectedFacets) {
		
		//convert selected facets into token lists
		List<String> categoryTokens = this.getFacetTokens(selectedFacets, Category.class); 
		List<String> brandTokens 	= this.getFacetTokens(selectedFacets, Brand.class);
		List<String> tagTokens 		= this.getFacetTokens(selectedFacets, Tag.class);
		
		//call the domain layer service to get a Page of Products
		Page<Product> pp  = this.findAll(	locale, 
										currency, 
										categoryDesc, 
										searchTerm, 
										page, 
										size, 
										sortBy, 
										categoryTokens, 
										brandTokens, 
										tagTokens);
		
		//add the page of objects to a new Search object and return it 
		Search search = new Search();
		search.setProducts(pp);
		search.setFacets(selectedFacets);
		return search;
	}
	
	@Override
	public NavFacet<Object> getMaxPrice(String categoryDesc, String locale, String currency, List<NavFacet> selectedFacets) {
		
		//convert selected facets into token lists
		List<Long> categoryIds = this.getFacetIds(selectedFacets, Category.class); 
		List<Long> brandIds = this.getFacetIds(selectedFacets, Brand.class);
		List<Long> tagIds = this.getFacetIds(selectedFacets, Tag.class);
	
		Double maxPrice = productService.getMaxPrice(categoryDesc, locale, ProductVars.MARKDOWN_SKU_DESCRIPTION, currency, categoryIds, brandIds, tagIds);
						  
		NavFacet<Object> s = new NavFacet<Object>();
		s.setToken(maxPrice.toString());
		s.setFacetingName(CategoryVars.PRICE_FACET_NAME);		
		
		return s;
	}
	
	private List<Facet> getDiscreteFacets(QueryBuilder qb, org.hibernate.search.jpa.FullTextQuery jpaQuery, String facetingName, String fieldReference) {		
		//create a category faceting request for the base level 
		FacetingRequest facetRequest = qb.facet()
				.name(facetingName)
				.onField(fieldReference) //in category class
				.discrete()
				.orderedBy(FacetSortOrder.COUNT_DESC)
				.includeZeroCounts(false)
				.createFacetingRequest();
		
		//add all the base level facets to categoryFacets List
		jpaQuery.getFacetManager().enableFaceting(facetRequest);
		return jpaQuery.getFacetManager().getFacets(facetingName);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	//@Cacheable
	public Page<Product> findAll(String lcl, 
								 String currency, 
								 String categoryDesc, 
								 String searchTerm, 
								 int page, 
								 int size, 
								 String sortBy, 
								 List<String> categoryTokens,
								 List<String> brandTokens,
								 List<String> tagTokens) {		
		
		FullTextEntityManager fullTextEntityManager = org.hibernate.search.jpa.Search.getFullTextEntityManager(em);
				
		String transLcl = lcl.substring(0, 2).toUpperCase() + lcl.substring(3, 5).toUpperCase();
		
		QueryBuilder productQueryBuilder = 
				  fullTextEntityManager.getSearchFactory()
				  .buildQueryBuilder()
				  .forEntity(ProductAttribute.class)	
				  .overridesForField("productDesc", lcl)
				  .get();
		
		//this is a Lucene query using the Lucene api
		org.apache.lucene.search.Query searchQuery = productQueryBuilder
													.bool()
													.must(productQueryBuilder.keyword()
													.onFields(
																 "primaryCategory.parent.parent.parent." + "primaryCategoryDesc" + transLcl,
																 "primaryCategory.parent.parent." + "primaryCategoryDesc" + transLcl,
																 "primaryCategory.parent." + "primaryCategoryDesc" + transLcl,
																 "primaryCategory.primaryCategoryDesc" + transLcl,
																 "secondaryCategory.parent.parent.parent." + "secondaryCategoryDesc" + transLcl,
																 "secondaryCategory.parent.parent." + "secondaryCategoryDesc" + transLcl,
																 "secondaryCategory.parent." + "secondaryCategoryDesc" + transLcl,
																 "secondaryCategory." + "secondaryCategoryDesc" + transLcl,
																 "product.brand.brandDesc" + transLcl,
																 "productDesc",
																 "tagA",
																 "tagB",
																 "tagC")
													.matching(searchTerm)													
													.createQuery())
													.must(productQueryBuilder.keyword()
													.onFields("lclCd")
													.matching(lcl)
													.createQuery())
													.createQuery();
		
		
		org.hibernate.search.jpa.FullTextQuery jpaQuery = fullTextEntityManager.createFullTextQuery(searchQuery, ProductAttribute.class);
		
		final Set<Facet> allFacets = new HashSet<Facet>();
		final Set<NavFacet<Category>> cs; 
		final Set<NavFacet<Brand>> bs;
		List<Facet> lf;
		
		//initialize the facets
		allFacets.addAll(this.getDiscreteFacets(productQueryBuilder, 	jpaQuery, CategoryVars.PRIMARY_CATEGORY_FACET_NAME, "primaryCategory.categoryToken"));
		allFacets.addAll(this.getDiscreteFacets(productQueryBuilder, 	jpaQuery, CategoryVars.PRIMARY_CATEGORY_FACET_NAME, "secondaryCategory.categoryToken"));
		allFacets.addAll(this.getDiscreteFacets(productQueryBuilder, 	jpaQuery, CategoryVars.BRAND_FACET_NAME, "brandCode"));
		allFacets.addAll(this.getRangeFacets(productQueryBuilder, 		jpaQuery, currency));
		allFacets.addAll(this.getDiscreteFacets(productQueryBuilder, 	jpaQuery, CategoryVars.TAG_FACET_NAME, "tagAFacet"));
		allFacets.addAll(this.getDiscreteFacets(productQueryBuilder, 	jpaQuery, CategoryVars.TAG_FACET_NAME, "tagBFacet"));
		allFacets.addAll(this.getDiscreteFacets(productQueryBuilder, 	jpaQuery, CategoryVars.TAG_FACET_NAME, "tagCFacet"));
		

		allFacets.addAll(allFacets.stream().filter(f -> f.getFacetingName().equals(CategoryVars.PRIMARY_CATEGORY_FACET_NAME)).map(f -> {
								return getParentCategoryFacets(new HashSet<Facet>(), f, productQueryBuilder, jpaQuery, lcl, currency);
		}).collect(Collectors.toSet()).stream().flatMap(Set::stream).collect(Collectors.toSet()));
		
		List<String> allTokens = new ArrayList<String>();
		allTokens.addAll(categoryTokens);
		allTokens.addAll(brandTokens);
		allTokens.addAll(tagTokens);
		
		//filter to get the facets that are selected
		lf = allTokens.stream().flatMap(x -> {
			return allFacets.stream().filter(y -> x.equals(y.getValue()));
		}).collect(Collectors.toList());
		
		cs = new HashSet<NavFacet<Category>>();
		lf.stream().forEach(f -> {
			jpaQuery.getFacetManager().getFacetGroup(f.getFacetingName()).selectFacets(FacetCombine.OR, f);
			processFacets(allFacets, productQueryBuilder, jpaQuery, currency, f.getFacetingName());
			if(f.getFacetingName().equals(CategoryVars.PRIMARY_CATEGORY_FACET_NAME)) {
				allFacets.addAll(getParentCategoryFacets(new HashSet<Facet>(), f, productQueryBuilder, jpaQuery, lcl, currency));
			}
		});
		
		lf = allTokens.stream().flatMap(x -> {
			return allFacets.stream().filter(y -> x.equals(y.getValue()));
		}).collect(Collectors.toList());
		
		lf.stream().forEach(f -> {
			jpaQuery.getFacetManager().getFacetGroup(f.getFacetingName()).selectFacets(FacetCombine.OR, f);
		});
		
		allFacets.stream().filter(f-> f.getFacetingName().equals(CategoryVars.PRIMARY_CATEGORY_FACET_NAME)).collect(Collectors.toList()).stream().forEach(cf ->  		{
													String categoryCode = (new LinkedList<String>(Arrays.asList(cf.getValue().split("/")))).getLast();
													NavFacet<Category> categoryFacet = convertCategoryToNavFacet(categoryCode, lcl, currency);
													categoryFacet.setProductCount(new Long(cf.getCount()));
													categoryFacet.setToken(cf.getValue());
													categoryFacet.setFacetType("discrete");
													categoryFacet.setFacetingName(cf.getFacetingName());
													categoryFacet.setFieldName(cf.getFieldName());
													cs.add(categoryFacet);
												});
		
		bs = new HashSet<NavFacet<Brand>>();
		allFacets.stream().filter(f-> f.getFacetingName().equals(CategoryVars.BRAND_FACET_NAME)).collect(Collectors.toList()).forEach(bf ->     {
													NavFacet<Brand> brandFacet = convertBrandToNavFacet(
													bf.getValue(), lcl, currency);
													brandFacet.setProductCount(new Long(bf.getCount()));
													brandFacet.setToken(bf.getValue());
													brandFacet.setFacetType("discrete");
													brandFacet.setFacetingName(bf.getFacetingName());
													brandFacet.setFieldName(bf.getFieldName());
													bs.add(brandFacet);
												});
		
		//for each of the baseline facets, convert them to Facet DTOs for the client and add them to "s" 
		final List<NavFacet<Object>> ps = new ArrayList<NavFacet<Object>>();
		allFacets.stream().filter(f-> f.getFacetingName().equals(CategoryVars.PRICE_FACET_NAME)).collect(Collectors.toList()).forEach(pf ->     {
													//pf.getValue();
													NavFacet<Object> pfDto = new NavFacet<Object>();
													pfDto.setProductCount(new Long(pf.getCount()));
													pfDto.setToken(pf.getValue());
													pfDto.setFacetType("range");
													pfDto.setFacetingName(pf.getFacetingName());
													pfDto.setFieldName(pf.getFieldName());
													ps.add(pfDto);
											   });
		
		
		final List<NavFacet<Tag>> ts = new ArrayList<NavFacet<Tag>>();
		allFacets.stream().filter(f-> f.getFacetingName().equals(CategoryVars.TAG_FACET_NAME)).collect(Collectors.toList()).forEach(tf ->     {
													//pf.getValue();
													NavFacet<Tag> tagFacet = new NavFacet<Tag>();
													tagFacet.setProductCount(new Long(tagFacet.getProductCount()));
													tagFacet.setToken(tagFacet.getToken());
													tagFacet.setFacetType("discrete");
													tagFacet.setFacetingName(tf.getFacetingName());
													tagFacet.setFieldName(tf.getFieldName());
													ts.add(tagFacet);
											   });
		
		
		//create a results container to send back to the client 
		//SearchDto src = new SearchDto();
		
		//set pageable definition for jpaQuery
		Pageable pageable = PageRequest.of(page, size);
		PageableUtil pageableUtil = new PageableUtil();
		jpaQuery.setFirstResult(pageableUtil.getStartPosition(pageable));
		jpaQuery.setMaxResults(pageable.getPageSize());
		
		//sort the results
		org.apache.lucene.search.Sort sort = getSortField(sortBy, currency);
		jpaQuery.setSort(sort);
		
		//get the results using jpaQuery object
		List<ProductAttribute> results = jpaQuery.getResultList();
				
		//convert the results of jpaQuery to product Data Transfer Objects 
		List<Product> lp = results.stream().map(pa -> productService.convertToProductDO(pa.getProduct(), lcl, currency)).collect(Collectors.toList());
		
		return new PageImpl<Product>(lp, pageable, jpaQuery.getResultSize());
	
	}
	
	 private Set<Facet> getParentCategoryFacets(Set<Facet> cfs, Facet sf, QueryBuilder qb, org.hibernate.search.jpa.FullTextQuery q, String locale, String currency) {
	    	if(sf == null) { return cfs; }
	    	
	    	String categoryCode = (new LinkedList<String>(Arrays.asList(sf.getValue().split("/")))).getLast();
	    	
	    	Optional<Category> c = Optional.ofNullable(categoryService.findOneByCode(
	    															locale, 
	    															CategoryVars.CATEGORY_TYPE_CODE_PRODUCT, 
	    															categoryCode));
	    	if(!c.isPresent()) { return cfs; }
	    	Optional<Category> parent = Optional.ofNullable(categoryService.findOne(locale, c.get().getParentId()));
	    	if(!parent.isPresent()) { return cfs; }
	    	
	    	//if we hit the root node, there are no parents
	    	if(parent.get().getCategoryCode().equals(CategoryVars.PRIMARY_HIERARCHY_ROOT_CODE)) { return cfs; }
	    	Long parentLevel = parent.get().getCategoryLevel();
	    	
	    	String frName = sf.getFacetingName();
	    	String frField = sf.getFieldName().split("\\.")[0] + StringUtils.repeat(".parent", c.get().getCategoryLevel().intValue() - parentLevel.intValue()) + ".categoryToken";

	    	Optional<Facet> parentFacet = this.getDiscreteFacets(qb, q, frName, frField).stream().filter(f -> f.getValue().equals(sf.getValue().replace("/" + categoryCode, ""))).findFirst();
	    	if(parentFacet.isPresent()) { cfs.add(parentFacet.get()); } else { return cfs; }
	    	return this.getParentCategoryFacets(cfs, parentFacet.get(), qb, q, locale, currency);
	    }
	    
	    @SuppressWarnings("unchecked")
		private List<Facet> getRangeFacets(QueryBuilder qb, org.hibernate.search.jpa.FullTextQuery jpaQuery, String currency) {
			
			org.apache.lucene.search.Sort sort = getSortField("priceDesc", currency);
			jpaQuery.setSort(sort);
			
			List<ProductAttribute> results = jpaQuery.getResultList();
			
			if(results.size() <= 0) { return new ArrayList<Facet>(); }
			
			Double maxPrice = results.get(0).getProduct().getCurrentMarkdownPriceHKD();
			Double minPrice = Lists.reverse(results).get(0).getProduct().getCurrentMarkdownPriceHKD();
			Double inc = (maxPrice > 0) ? (maxPrice - 
							((minPrice.equals(maxPrice)) ? 0 : minPrice)  
					) / 4 : maxPrice;
			
			inc = new BigDecimal(inc).setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
			
			Double 	below 	= inc, 
					froma 	= (new BigDecimal(inc + new Double(0.01)).setScale(2, BigDecimal.ROUND_DOWN).doubleValue()), 
					toa 	= (new BigDecimal(inc * 2).setScale(2, BigDecimal.ROUND_DOWN).doubleValue()), 
					fromb 	= (new BigDecimal(toa + new Double(0.01)).setScale(2, BigDecimal.ROUND_DOWN).doubleValue()), 
					tob 	= (new BigDecimal(inc * 4).setScale(2, BigDecimal.ROUND_DOWN).doubleValue()),
					above 	= tob;
			
			FacetingRequest facetRequest = qb.facet()
					.name(CategoryVars.PRICE_FACET_NAME)
					.onField("product.currentMarkdownPrice" + currency + "Facet") //In product class
					.range()
					.below(below)
					.from(froma).to(toa)
					.from(fromb).to(tob)
					.above(above)
					.orderedBy(FacetSortOrder.RANGE_DEFINITION_ORDER)
					.createFacetingRequest();
			
			jpaQuery.getFacetManager().enableFaceting(facetRequest);
			return jpaQuery.getFacetManager().getFacets(CategoryVars.PRICE_FACET_NAME);
		}
		
		private Set<Facet> processFacets(Set<Facet> allFacets, QueryBuilder qb, org.hibernate.search.jpa.FullTextQuery jpaQuery, String currency, String facetingName) {
			List<Facet> processlf = allFacets.stream().filter(c -> (!c.getFacetingName().equals(facetingName))).collect(Collectors.toList());
			allFacets.removeAll(processlf);
			allFacets.addAll(processlf.stream().map(pf -> {
						return (pf.getFacetingName().equals(CategoryVars.PRICE_FACET_NAME)) 
						? this.getRangeFacets(qb, jpaQuery, currency)
						: this.getDiscreteFacets(qb, jpaQuery, pf.getFacetingName(), pf.getFieldName());
						
				}).collect(Collectors.toList()).stream().flatMap(List::stream).collect(Collectors.toSet()));
			return allFacets;
		}
	
    public NavFacet<Category> convertCategoryToNavFacet(String categoryCode, String locale, String currency) {
    	NavFacet<Category> cf = new NavFacet<Category>();
    	Category c = categoryService.findOneByCode(locale, CategoryVars.CATEGORY_TYPE_CODE_PRODUCT, categoryCode);
    	if(c == null) { return cf; }
    	cf.setId(c.getCategoryId());
    	cf.setPayload(c);
    	return cf;		
    }
	
	 private org.apache.lucene.search.Sort getSortField(String field, String currency) {
			switch(field) {
			case "nameAsc":
				return new org.apache.lucene.search.Sort(new SortField(getSortFieldName(field, currency), SortField.Type.STRING, false));
			case "nameDesc":
				return new org.apache.lucene.search.Sort(new SortField(getSortFieldName(field, currency), SortField.Type.STRING, true));
			case "priceAsc":
				return new org.apache.lucene.search.Sort(new SortedNumericSortField(getSortFieldName(field, currency), SortField.Type.DOUBLE, false	));
			case "priceDesc":
				return new org.apache.lucene.search.Sort(new SortedNumericSortField(getSortFieldName(field, currency), SortField.Type.DOUBLE, true));
			default: 
				return new org.apache.lucene.search.Sort(new SortField(getSortFieldName(field, currency), SortField.Type.STRING, true));
			}
		}
	    
	    private String getSortFieldName(String field, String currency) {
			switch(field) {
			case "nameAsc":
				return "product.productDesc";
			case "nameDesc":
				return "product.productDesc";
			case "priceDesc":
				return "product.currentMarkdownPrice" + currency;
			case "priceAsc":
				return "product.currentMarkdownPrice" + currency;
			default: 
				return "product.productDesc";
			}
		}
	    
	    public NavFacet<Brand> convertBrandToNavFacet(String brandCode, String lcl, String currency) {
	    	Brand b = brandService.findOneByCode(lcl, brandCode);
	    	NavFacet<Brand> bf = new NavFacet<Brand>();
	    	bf.setId(b.getBrandId());
	    	bf.setPayload(b);
	    	return bf;
	    } 
		
}
