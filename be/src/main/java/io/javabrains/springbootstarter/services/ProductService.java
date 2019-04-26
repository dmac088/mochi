package io.javabrains.springbootstarter.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.SortedNumericSortField;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.hibernate.search.query.engine.spi.FacetManager;
import org.hibernate.search.query.facet.Facet;
import org.hibernate.search.query.facet.FacetSelection;
import org.hibernate.search.query.facet.FacetSortOrder;
import org.hibernate.search.query.facet.FacetingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;

import io.javabrains.springbootstarter.domain.Product;
import io.javabrains.springbootstarter.dto.CategorySidebar;
import io.javabrains.springbootstarter.entity.Category;
import io.javabrains.springbootstarter.entity.CategoryRepository;
import io.javabrains.springbootstarter.entity.PageableUtil;
import io.javabrains.springbootstarter.entity.ProductAttribute;
import io.javabrains.springbootstarter.entity.ProductAttributeRepository;
import io.javabrains.springbootstarter.entity.ProductPagingAndSortingRepository;
import io.javabrains.springbootstarter.entity.ProductPriceRepository;
import io.javabrains.springbootstarter.entity.ProductRepository;

@Service
@Transactional
@CacheConfig(cacheNames="products")
public class ProductService implements IProductService {

    @Autowired
    private ProductPagingAndSortingRepository productPagingAndSortingRepository;
    
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired ProductPriceRepository productPriceRepository;
    
    @Autowired
    private ProductAttributeRepository productAttributeRepository;
    
    @Autowired
    private CategoryRepository productCategoryRepository;
    
	@PersistenceContext(unitName = "mochiEntityManagerFactory")
	private EntityManager em;
    
    // API
    //This method should accept a DTO and return a DTO
    //The DTO is coarse grained and contains a flat structure of properties
    //if we did not use a DTO we would have JSON nesting as per the domain model structure, which is hard to manage in our client views
    //The DTO is simple and dumb, it is the service layer that manages the translation between DTO and domain objects
    
    @Override
	@Transactional
	@Cacheable
	public ResultContainer getProducts(String lcl, String currency, int page, int size, String sortBy) {
    	Page<Product> pp;
		Page<io.javabrains.springbootstarter.entity.Product> ppa = productPagingAndSortingRepository.findAll(PageRequest.of(page, size, this.sortByParam(sortBy)));
		pp = ppa.map(p -> this.convertToProductDto(p, lcl, currency));
		ResultContainer rc = new ResultContainer();
		rc.setProducts(pp);
		return rc;
	}	
    
    @Override
	@Transactional
	@Cacheable
	public Product getProduct(String lcl, String currency, Long id) {
    	io.javabrains.springbootstarter.entity.Product pa = productRepository.findById(id).get();
		Product p = this.convertToProductDto(pa, lcl, currency);
		return p;
	}	

    
    @Override
  	@Transactional
  	@Cacheable
  	public List<Product> getPreviewProductsForCategory(String lcl, String currency, Long categoryId) {
     	List<Category> pcl = new ArrayList<Category>();
     	Category pc = productCategoryRepository.findByCategoryId(categoryId);
     	recurseCategories(pcl, pc);
     	List<Long> categoryIds = pcl.stream().map(sc -> sc.getCategoryId()).collect(Collectors.toList());
  		List<io.javabrains.springbootstarter.entity.Product> ppa = productRepository.findByCategoriesCategoryIdIn(categoryIds);
  		List<Product> pp = ppa.stream().map(pa -> this.convertToProductDto(pa, lcl, currency)).collect(Collectors.toList());
  		return pp;
  	}	
    
	@Override
	@Cacheable
	public ResultContainer getProductsForCategory(String lcl, String currency, String categoryDesc, int page, int size, String sortBy) {
		List<Category> pcl = new ArrayList<Category>();
     	Category pc = productCategoryRepository.findByAttributesCategoryDesc(categoryDesc);
     	recurseCategories(pcl, pc);
     	List<Long> categoryIds = pcl.stream().map(sc -> sc.getCategoryId()).collect(Collectors.toList());
  		Page<io.javabrains.springbootstarter.entity.Product> ppa = productPagingAndSortingRepository.findByCategoriesCategoryIdInAndAttributesLclCd(categoryIds, lcl, PageRequest.of(page, size, this.sortByParam(sortBy)));
  		Page<Product> pp = ppa.map(pa -> this.convertToProductDto(pa, lcl, currency));
  		ResultContainer rc = new ResultContainer();
		rc.setProducts(pp);
		return rc;
	}
	
	@Override
	@Cacheable
	public ResultContainer getProductsForCategory(String lcl, String currency, String categoryDesc, Double price, int page, int size, String sortBy) {
		System.out.println("getProductsForCategory");
		List<Category> pcl = new ArrayList<Category>();
     	Category pc = productCategoryRepository.findByAttributesCategoryDesc(categoryDesc);
     	recurseCategories(pcl, pc);
     	List<Long> categoryIds = pcl.stream().map(sc -> sc.getCategoryId()).collect(Collectors.toList());
  		Page<io.javabrains.springbootstarter.entity.Product> ppa = productPagingAndSortingRepository.findByCategoriesCategoryIdInAndAttributesLclCdAndPricesPriceValueBetween(categoryIds, lcl, new Double(0), price, PageRequest.of(page, size, this.sortByParam(sortBy)));
  		Page<Product> pp = ppa.map(pa -> this.convertToProductDto(pa, lcl, currency));
  		ResultContainer rc = new ResultContainer();
		rc.setProducts(pp);
		return rc;
	}
	
	@Override
	@Cacheable
	public ResultContainer getProductsForCategoryAndBrand(String lcl, String currency, String categoryDesc, String brandDesc, int page, int size, String sortBy) {
		System.out.println("getProductsForCategoryAndBrand");
		List<Category> pcl = new ArrayList<Category>();
     	Category pc = productCategoryRepository.findByAttributesCategoryDesc(categoryDesc);
     	recurseCategories(pcl, pc);
     	List<Long> categoryIds = pcl.stream().map(sc -> sc.getCategoryId()).collect(Collectors.toList());
  		Page<io.javabrains.springbootstarter.entity.Product> ppa = productPagingAndSortingRepository.findByCategoriesCategoryIdInAndAttributesLclCdAndBrandBrandAttributesBrandDescAndBrandBrandAttributesLclCd(categoryIds, lcl, brandDesc, lcl, PageRequest.of(page, size, this.sortByParam(sortBy)));		
  		Page<Product> pp = ppa.map(pa -> this.convertToProductDto(pa, lcl, currency));
  		ResultContainer rc = new ResultContainer();
		rc.setProducts(pp);
		return rc;
	}
	
	@Override
 	@Cacheable
	public ResultContainer getProductsForCategoryAndPrice(String lcl, String currency, String categoryDesc, Double price, int page, int size, String sortBy) {
		System.out.println("getProductsForCategoryAndBrandAndPrice");
		System.out.println(sortBy);
		List<Category> pcl = new ArrayList<Category>();
     	Category pc = productCategoryRepository.findByAttributesCategoryDesc(categoryDesc);
     	recurseCategories(pcl, pc);
     	List<Long> categoryIds = pcl.stream().map(sc -> sc.getCategoryId()).collect(Collectors.toList());
  		Page<io.javabrains.springbootstarter.entity.Product> ppa = productPagingAndSortingRepository.findByCategoriesCategoryIdInAndAttributesLclCdAndPricesPriceValueBetweenAndPricesTypeDescAndPricesCurrencyCodeAndPricesStartDateLessThanAndPricesEndDateGreaterThan(categoryIds, lcl, new Double(0), price, "markdown", currency, new Date(), new Date(),PageRequest.of(page, size, this.sortByParam(sortBy)));
  		Page<Product> pp = ppa.map(pa -> this.convertToProductDto(pa, lcl, currency));
  		ResultContainer rc = new ResultContainer();
		rc.setProducts(pp);
		return rc;
	}

	
	@Override
	@Cacheable
	public ResultContainer getProductsForCategoryAndBrandAndPrice(String lcl, String currency, String categoryDesc, String brandDesc, Double price, int page, int size, String sortBy) {
		System.out.println("getProductsForCategoryAndBrandAndPrice");
		System.out.println(sortBy);
		List<Category> pcl = new ArrayList<Category>();
     	Category pc = productCategoryRepository.findByAttributesCategoryDesc(categoryDesc);
     	recurseCategories(pcl, pc);
     	List<Long> categoryIds = pcl.stream().map(sc -> sc.getCategoryId()).collect(Collectors.toList());
  		Page<io.javabrains.springbootstarter.entity.Product> ppa = productPagingAndSortingRepository.findByCategoriesCategoryIdInAndAttributesLclCdAndBrandBrandAttributesBrandDescAndBrandBrandAttributesLclCdAndPricesPriceValueBetweenAndPricesTypeDescAndPricesCurrencyCodeAndPricesStartDateLessThanAndPricesEndDateGreaterThan(categoryIds, lcl, brandDesc, lcl, new Double(0), price, "markdown", currency, new Date(), new Date(),PageRequest.of(page, size, this.sortByParam(sortBy)));
  		Page<Product> pp = ppa.map(pa -> this.convertToProductDto(pa, lcl, currency));
  		ResultContainer rc = new ResultContainer();
		rc.setProducts(pp);
		return rc;
	}

	@Cacheable
	@SuppressWarnings("unchecked")
	public ResultContainer findProduct(String lcl, String currency, String categoryDesc, String searchTerm, int page, int size, String sortBy, List<CategorySidebar> receivedFacets) {		
		
		FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(em);
				
		QueryBuilder productQueryBuilder = 
				fullTextEntityManager.getSearchFactory()
				  .buildQueryBuilder()
				  .forEntity(ProductAttribute.class)
				  .get();
		
		//	this is a lucene query using the lucene api
		org.apache.lucene.search.Query searchQuery = productQueryBuilder
													.bool()
													.must(productQueryBuilder.keyword()
													.onFields(
																 "product.categories.parent.parent.parent.attributes.categoryDesc",
																 "product.categories.parent.parent.attributes.categoryDesc",
																 "product.categories.parent.attributes.categoryDesc",
																 "product.categories.attributes.categoryDesc",
																 "product.brand.brandAttributes.brandDesc",
																 "productDesc")
													.matching(searchTerm)													
													.createQuery())
													.must(productQueryBuilder.keyword()
													.onFields(	 "lclCd")
													.matching(lcl)
													.createQuery())
													.createQuery();
		
		
		org.hibernate.search.jpa.FullTextQuery jpaQuery
		  = fullTextEntityManager.createFullTextQuery(searchQuery, ProductAttribute.class);
		
		//declare a set of facets (we need to avoid Duplicates)
		Set<Facet> categoryFacets = new HashSet<Facet>(); 
		
		//create a category faceting request for the base level 
		FacetingRequest categoryFacetRequest = productQueryBuilder.facet()
				.name("CategoryDescFR")
				.onField("primaryCategory.categoryToken")
				.discrete()
				.orderedBy(FacetSortOrder.COUNT_DESC)
				.includeZeroCounts(false)
				.maxFacetCount(5)
				.createFacetingRequest();
		
		//add all the base level facets to categoryFacets List
		FacetManager facetMgr = jpaQuery.getFacetManager();
		facetMgr.enableFaceting(categoryFacetRequest);
		categoryFacets.addAll(facetMgr.getFacets("CategoryDescFR"));
		
		//run the query and get the results
		List<ProductAttribute> results =  Collections.checkedList(jpaQuery.getResultList(), ProductAttribute.class);
	
		//convert the results to product DTOs and store in a list
		//List<Product> lp;// = results.stream().map(pa -> this.convertToProductDto(pa.getProduct(), lcl, currency)).collect(Collectors.toList());
		
		//create a results container to send back to the client 
		ResultContainer src = new ResultContainer();
		
		//create a hashset of FacetDTOs to send back to the client, with additional metadata
		Set<CategorySidebar> s = new HashSet<CategorySidebar>();
		
		//for each of the baseline facets (5x), convert them to Facet DTOs for the client and add them to "s" 
		categoryFacets.stream().forEach(cf ->  {
													String categoryCode = (new LinkedList<String>(Arrays.asList(cf.getValue().split("/")))).getLast();
													CategorySidebar c
														= convertToCategoryFacet(categoryCode, lcl, currency);
													c.setCount(new Long(cf.getCount()));
													c.setToken(cf.getValue());
													c.setName(cf.getFacetingName());
													c.setFieldName(cf.getFieldName());
													s.add(c);
											   });
		
		//create parent category Facet DTOs
		(new HashSet<CategorySidebar>(s)).stream().forEach(cf -> {
			createParentCategoryFacets(categoryFacets, s, cf, productQueryBuilder, jpaQuery, lcl, currency, cf.getLevel());
		});
		
		
		FacetSelection facetSelection = facetMgr.getFacetGroup("CategoryDesc");
		
		//get a list of facets that exist in the list of received facets
		List<Facet> lf =
		receivedFacets.stream().flatMap(x -> 
			categoryFacets.stream().filter(y -> 
				x.getToken().equals(y.getValue())).limit(1)).collect(Collectors.toList());
		
		//apply the selected facets to the facet selection object
		facetSelection.selectFacets( lf.toArray(new Facet[0]) );
		 
		//set pageable definition for jpaQuery
		Pageable pageable = PageRequest.of(page, size);
		PageableUtil pageableUtil = new PageableUtil();
		jpaQuery.setFirstResult(pageableUtil.getStartPosition(pageable));
		jpaQuery.setMaxResults(pageable.getPageSize());
		
		//sort the results
		org.apache.lucene.search.Sort sort = getSortField(sortBy);
		jpaQuery.setSort(sort);
		
		//get the results using jpaQuery object
		results =  Collections.checkedList(jpaQuery.getResultList(), ProductAttribute.class);
				
		//convert the results of jpaQuery to product Data Transfer Objects 
		List<Product> lp = results.stream().map(pa -> this.convertToProductDto(pa.getProduct(), lcl, currency)).collect(Collectors.toList());
		
		//create a paging object to hold the results of jpaQuery 
		Page<Product> pp = new PageImpl<Product>(lp, pageable, jpaQuery.getResultSize());
		
		src.setCategoryFacets(new ArrayList<CategorySidebar>(s));
		
		src.setProducts(pp);
		
		return src;
	}
	
    public CategorySidebar convertToCategoryFacet(String categoryCode, String lcl, String currency) {
    	Category c = productCategoryRepository.findByCategoryCode(categoryCode);
    	CategorySidebar cf = new CategorySidebar();
    	cf.setId(c.getCategoryId());
    	cf.setDesc(c.getAttributes().stream().filter(ca -> ca.getLclCd().equals(lcl)).collect(Collectors.toList()).get(0).getCategoryDesc());
    	cf.setLevel(c.getCategoryLevel());
    	if(c.getParent() != null) {
    		cf.setParentId(c.getParent().getCategoryId());
    	}
    	return cf;		
    }
    
    private void createParentCategoryFacets(Set<Facet> cfs, Set<CategorySidebar> sc, CategorySidebar c, QueryBuilder qb, org.hibernate.search.jpa.FullTextQuery q, String lcl, String currency, Long baseLevel) {
    	if(c == null) { return; }
    	if(c.getParentId() == null) { return; }
    	
    	Category p = productCategoryRepository.findByCategoryId(c.getParentId());
    	CategorySidebar pcf = convertToCategoryFacet(p.getCategoryCode(), lcl, currency);
    	String frName = "CategoryDesc";
    	String frField = "primaryCategory" + StringUtils.repeat(".parent", baseLevel.intValue() - p.getCategoryLevel().intValue()) + ".categoryToken";
    		
    	FacetingRequest categoryFacetRequest = qb.facet()
    	.name(frName)
    	.onField(frField)
    	.discrete()
    	.orderedBy(FacetSortOrder.COUNT_DESC)
    	.createFacetingRequest();
    		
    	FacetManager facetMgr = q.getFacetManager();
    	cfs.addAll(facetMgr.getFacets(frName));
    	facetMgr.enableFaceting(categoryFacetRequest);
    	pcf.setToken(String.join("/", Arrays.copyOfRange(c.getToken().split("/"), 0, c.getLevel().intValue()+1)));
    	Facet tmp = facetMgr.getFacets(frName).stream().filter(f -> f.getValue().equals(pcf.getToken())).collect(Collectors.toList()).get(0);
    	pcf.setCount(new Long(tmp.getCount()));
		pcf.setName(tmp.getFacetingName());
		pcf.setFieldName(tmp.getFieldName());
    	sc.add(pcf);
    	this.createParentCategoryFacets(cfs, sc, pcf, qb, q, lcl, currency, baseLevel);
    }
    
    public Product convertToProductDto(final io.javabrains.springbootstarter.entity.Product product, String lcl, String currency) {
    	ProductAttribute pa = productAttributeRepository.findByLclCdAndProductId(lcl, product.getProductId());
        final Product pDto = new Product();
        pDto.setProductId(product.getProductId());
        pDto.setProductCreateDt(product.getProductCreateDt());
        pDto.setProductUPC(product.getProductUPC());
        pDto.setProductDesc(pa.getProductDesc());
        pDto.setProductRetail(productPriceRepository.findByProductProductIdAndTypeDescAndStartDateLessThanEqualAndEndDateGreaterThanEqualAndCurrencyCode(product.getProductId(), "retail", new Date(), new Date(), currency).getPriceValue());
        pDto.setProductMarkdown(productPriceRepository.findByProductProductIdAndTypeDescAndStartDateLessThanEqualAndEndDateGreaterThanEqualAndCurrencyCode(product.getProductId(), "markdown", new Date(), new Date(), currency).getPriceValue());
        pDto.setProductImage(pa.getProductImage());
        pDto.setLclCd(lcl);
        pDto.setBrandDesc(product.getBrand().getBrandAttributes().stream()
        .filter( ba -> ba.getLclCd().equals(lcl)).collect(Collectors.toList()).get(0).getBrandDesc());
        return pDto;
    }
    
    private String getSortFieldName(String field) {
		switch(field) {
		case "nameAsc":
			return "productSortDesc";
		case "nameDesc":
			return "productSortDesc";
		case "priceDesc":
			return "product.currentMarkdownPriceHKD";
		case "priceAsc":
			return "product.currentMarkdownPriceHKD";
		default: 
			return "productSortDesc";
		}
	}
	
	private org.apache.lucene.search.Sort getSortField(String field) {
		switch(field) {
		case "nameAsc":
			return new org.apache.lucene.search.Sort(new SortField(getSortFieldName(field), SortField.Type.STRING, true));
		case "nameDesc":
			return new org.apache.lucene.search.Sort(new SortField(getSortFieldName(field), SortField.Type.STRING, true));
		case "priceAsc":
			return new org.apache.lucene.search.Sort(new SortedNumericSortField(getSortFieldName(field), SortField.Type.DOUBLE, false));
		case "priceDesc":
			return new org.apache.lucene.search.Sort(new SortedNumericSortField(getSortFieldName(field), SortField.Type.DOUBLE, true));
		default: 
			return new org.apache.lucene.search.Sort(new SortField(getSortFieldName(field), SortField.Type.STRING, true));
		}
	}
	
	private Sort sortByParam(String param) {
    	switch (param) {
    	case "priceAsc": return new Sort(Sort.Direction.ASC, "prices.PriceValue");
    	case "priceDesc": return new Sort(Sort.Direction.DESC, "prices.PriceValue");
    	case "nameAsc": return Sort.by(new Sort.Order(Sort.Direction.ASC, "attributes.productDesc").ignoreCase()) ;
    	case "nameDesc": return Sort.by(new Sort.Order(Sort.Direction.DESC, "attributes.productDesc").ignoreCase());
    	default: return Sort.by(new Sort.Order(Sort.Direction.ASC, "attributes.productDesc").ignoreCase());
    	}
    }
	
    public void recurseCategories(List<Category> pcl, Category pc) {
    	pcl.add(pc);
    	pc.getChildren().forEach(child -> recurseCategories(pcl, child));
    }
}