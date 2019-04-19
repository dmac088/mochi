package io.javabrains.springbootstarter.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.apache.lucene.search.SortField;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.hibernate.search.query.engine.spi.FacetManager;
import org.hibernate.search.query.facet.Facet;
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
import io.javabrains.springbootstarter.domain.PageableUtil;
import io.javabrains.springbootstarter.services.Product;
import io.javabrains.springbootstarter.domain.ProductAttribute;
import io.javabrains.springbootstarter.domain.ProductAttributeRepository;
import io.javabrains.springbootstarter.domain.Category;
import io.javabrains.springbootstarter.domain.CategoryRepository;
import io.javabrains.springbootstarter.domain.ProductPagingAndSortingRepository;
import io.javabrains.springbootstarter.domain.ProductPriceRepository;
import io.javabrains.springbootstarter.domain.ProductRepository;

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
		Page<io.javabrains.springbootstarter.domain.Product> ppa = productPagingAndSortingRepository.findAll(PageRequest.of(page, size, this.sortByParam(sortBy)));
		pp = ppa.map(p -> this.convertTopDto(p, lcl, currency));
		ResultContainer rc = new ResultContainer();
		rc.setProducts(pp);
		return rc;
	}	
    
    @Override
	@Transactional
	@Cacheable
	public Product getProduct(String lcl, String currency, Long id) {
    	io.javabrains.springbootstarter.domain.Product pa = productRepository.findById(id).get();
		Product p = this.convertTopDto(pa, lcl, currency);
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
  		List<io.javabrains.springbootstarter.domain.Product> ppa = productRepository.findByCategoriesCategoryIdIn(categoryIds);
  		List<Product> pp = ppa.stream().map(pa -> this.convertTopDto(pa, lcl, currency)).collect(Collectors.toList());
  		return pp;
  	}	
    
	@Override
	@Cacheable
	public ResultContainer getProductsForCategory(String lcl, String currency, String categoryDesc, int page, int size, String sortBy) {
		List<Category> pcl = new ArrayList<Category>();
     	Category pc = productCategoryRepository.findByAttributesCategoryDesc(categoryDesc);
     	recurseCategories(pcl, pc);
     	List<Long> categoryIds = pcl.stream().map(sc -> sc.getCategoryId()).collect(Collectors.toList());
  		Page<io.javabrains.springbootstarter.domain.Product> ppa = productPagingAndSortingRepository.findByCategoriesCategoryIdInAndAttributesLclCd(categoryIds, lcl, PageRequest.of(page, size, this.sortByParam(sortBy)));
  		Page<Product> pp = ppa.map(pa -> this.convertTopDto(pa, lcl, currency));
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
  		Page<io.javabrains.springbootstarter.domain.Product> ppa = productPagingAndSortingRepository.findByCategoriesCategoryIdInAndAttributesLclCdAndPricesPriceValueBetween(categoryIds, lcl, new Double(0), price, PageRequest.of(page, size, this.sortByParam(sortBy)));
  		Page<Product> pp = ppa.map(pa -> this.convertTopDto(pa, lcl, currency));
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
  		Page<io.javabrains.springbootstarter.domain.Product> ppa = productPagingAndSortingRepository.findByCategoriesCategoryIdInAndAttributesLclCdAndBrandBrandAttributesBrandDescAndBrandBrandAttributesLclCd(categoryIds, lcl, brandDesc, lcl, PageRequest.of(page, size, this.sortByParam(sortBy)));		
  		Page<Product> pp = ppa.map(pa -> this.convertTopDto(pa, lcl, currency));
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
  		Page<io.javabrains.springbootstarter.domain.Product> ppa = productPagingAndSortingRepository.findByCategoriesCategoryIdInAndAttributesLclCdAndPricesPriceValueBetweenAndPricesTypeDescAndPricesCurrencyCodeAndPricesStartDateLessThanAndPricesEndDateGreaterThan(categoryIds, lcl, new Double(0), price, "markdown", currency, new Date(), new Date(),PageRequest.of(page, size, this.sortByParam(sortBy)));
  		Page<Product> pp = ppa.map(pa -> this.convertTopDto(pa, lcl, currency));
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
  		Page<io.javabrains.springbootstarter.domain.Product> ppa = productPagingAndSortingRepository.findByCategoriesCategoryIdInAndAttributesLclCdAndBrandBrandAttributesBrandDescAndBrandBrandAttributesLclCdAndPricesPriceValueBetweenAndPricesTypeDescAndPricesCurrencyCodeAndPricesStartDateLessThanAndPricesEndDateGreaterThan(categoryIds, lcl, brandDesc, lcl, new Double(0), price, "markdown", currency, new Date(), new Date(),PageRequest.of(page, size, this.sortByParam(sortBy)));
  		Page<Product> pp = ppa.map(pa -> this.convertTopDto(pa, lcl, currency));
  		ResultContainer rc = new ResultContainer();
		rc.setProducts(pp);
		return rc;
	}

	public ResultContainer findProduct(String lcl, String currency, String categoryDesc, String searchTerm, int page, int size, String sortBy, CustomFacet[] selectedFacets) {

		System.out.println(lcl);
		
		PageableUtil pageableUtil = new PageableUtil();
		
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
		
		
		FacetingRequest categoryFacetRequest = productQueryBuilder.facet()
		.name("CategoryDescFR")
		.onField("primaryCategory.parent.categoryCode")
		.discrete()
		.orderedBy(FacetSortOrder.COUNT_DESC)
		.includeZeroCounts(false)
		.maxFacetCount(5)
		.createFacetingRequest();
		
		FacetingRequest brandFacetRequest = productQueryBuilder.facet()
				.name("BrandDescFR")
				.onField("brandDesc")
				.discrete()
				.orderedBy(FacetSortOrder.COUNT_DESC)
				.includeZeroCounts(false)
				.maxFacetCount(5)
				.createFacetingRequest();
		
		List<Facet> categoryFacets = new ArrayList<Facet>(), brandFacets = new ArrayList<Facet>(); 
		FacetManager facetMgr = jpaQuery.getFacetManager();
		facetMgr.enableFaceting(categoryFacetRequest);
		categoryFacets.addAll(facetMgr.getFacets("CategoryDescFR"));
		facetMgr.enableFaceting(brandFacetRequest);
		brandFacets.addAll(facetMgr.getFacets("BrandDescFR"));
		
//		filtering on a specific facet is as easy as..... 
//		FacetSelection facetSelection = facetMgr.getFacetGroup("BrandDescFR");
//		Facet facet = facets.stream().filter(f -> f.getValue().equals("Driscolls")).collect(Collectors.toList()).get(0);
//		facetSelection.selectFacets(facet);
		
//		facets.stream().forEach(f -> { 
//								System.out.println("Facet field = " +  f.getFieldName() + " value = " + f.getValue() + " - count = " + f.getCount());
//							});
//		
		Pageable pageable = PageRequest.of(page, size);
		jpaQuery.setFirstResult(pageableUtil.getStartPosition(pageable));
		jpaQuery.setMaxResults(pageable.getPageSize());
			
		System.out.println("sort field = " + getSortField(sortBy));
		System.out.println("sort field type = " + getSortFieldType(sortBy).toString());
		//sorting
		org.apache.lucene.search.Sort sort = new org.apache.lucene.search.Sort(new SortField(getSortField(sortBy), getSortFieldType(sortBy)));
		jpaQuery.setSort(sort);
		
		@SuppressWarnings("unchecked")
		List<ProductAttribute> results =  Collections.checkedList(jpaQuery.getResultList(), ProductAttribute.class);
		
//		for (ProductAttribute p : results) {
//			  System.out.println(p);
//			}
		
		List<Product> lp = results.stream().map(pa -> this.convertTopDto(pa.getProduct(), lcl, currency)).collect(Collectors.toList());
		
		
		Page<Product> pp = new PageImpl<Product>(lp, pageable, jpaQuery.getResultSize());
		
		ResultContainer src = new ResultContainer();
		src.setBrandFacets(brandFacets);
		src.setCategoryFacets(categoryFacets);
		src.setProducts(pp);
		
		return src;
	}
	
	private String getSortField(String field) {
		switch(field) {
		case "nameAsc":
			return "productSortDesc";
		case "nameDesc":
			return "productSortDesc";
		case "priceDesc":
			return "product.prices.priceValue";
		case "priceAsc":
			return "product.prices.priceValue";
		default: 
			return "productSortDesc";
		}
	}
	
	private SortField.Type getSortFieldType(String field) {
		switch(field) {
		case "nameAsc":
			return SortField.Type.STRING;
		case "nameDesc":
			return SortField.Type.STRING;
		case "priceAsc":
			return SortField.Type.DOUBLE;
		case "priceDesc":
			return SortField.Type.DOUBLE;
		default: 
			return SortField.Type.STRING;
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
    
    public Product convertTopDto(final io.javabrains.springbootstarter.domain.Product product, String lcl, String currency) {
        //get values from contact entity and set them in contactDto
        //e.g. contactDto.setContactId(contact.getContactId());
    	
    	ProductAttribute pa = productAttributeRepository.findByLclCdAndProductId(lcl, product.getProductId());
    	
        final Product pDto = new Product();
        pDto.setProductId(product.getProductId());
        pDto.setProductCreateDt(product.getProductCreateDt());
        pDto.setProductUPC(product.getProductUPC());
        pDto.setProductDesc(pa.getProductDesc());
  
        
        /*instead of filtering the stream use JPA queries*/
        //pDto.setProductRetail(productAttribute.getProduct().getProductPrices().stream().filter(p -> p.getPriceCurrency().getCurrencyCode().equals(currency) && p.getPriceType().getPriceTypeDesc().equals("retail")).collect(Collectors.toList()).get(0).getPriceValue());
        //pDto.setProductMarkdown(productAttribute.getProduct().getProductPrices().stream().filter(p -> p.getPriceCurrency().getCurrencyCode().equals(currency) && p.getPriceType().getPriceTypeDesc().equals("markdown")).collect(Collectors.toList()).get(0).getPriceValue());
                
        pDto.setProductRetail(productPriceRepository.findByProductProductIdAndTypeDescAndStartDateLessThanEqualAndEndDateGreaterThanEqualAndCurrencyCode(product.getProductId(), "retail", new Date(), new Date(), currency).getPriceValue());
        pDto.setProductMarkdown(productPriceRepository.findByProductProductIdAndTypeDescAndStartDateLessThanEqualAndEndDateGreaterThanEqualAndCurrencyCode(product.getProductId(), "markdown", new Date(), new Date(), currency).getPriceValue());
        
        pDto.setProductImage(pa.getProductImage());
        pDto.setLclCd(lcl);
        pDto.setBrandDesc(product.getBrand().getBrandAttributes().stream()
        .filter( ba -> ba.getLclCd().equals(lcl)).collect(Collectors.toList()).get(0).getBrandDesc());
        
        return pDto;
    }
}