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
import io.javabrains.springbootstarter.facets.CategoryFacet;

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
		pp = ppa.map(p -> this.convertToProductDto(p, lcl, currency));
		ResultContainer rc = new ResultContainer();
		rc.setProducts(pp);
		return rc;
	}	
    
    @Override
	@Transactional
	@Cacheable
	public Product getProduct(String lcl, String currency, Long id) {
    	io.javabrains.springbootstarter.domain.Product pa = productRepository.findById(id).get();
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
  		List<io.javabrains.springbootstarter.domain.Product> ppa = productRepository.findByCategoriesCategoryIdIn(categoryIds);
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
  		Page<io.javabrains.springbootstarter.domain.Product> ppa = productPagingAndSortingRepository.findByCategoriesCategoryIdInAndAttributesLclCd(categoryIds, lcl, PageRequest.of(page, size, this.sortByParam(sortBy)));
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
  		Page<io.javabrains.springbootstarter.domain.Product> ppa = productPagingAndSortingRepository.findByCategoriesCategoryIdInAndAttributesLclCdAndPricesPriceValueBetween(categoryIds, lcl, new Double(0), price, PageRequest.of(page, size, this.sortByParam(sortBy)));
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
  		Page<io.javabrains.springbootstarter.domain.Product> ppa = productPagingAndSortingRepository.findByCategoriesCategoryIdInAndAttributesLclCdAndBrandBrandAttributesBrandDescAndBrandBrandAttributesLclCd(categoryIds, lcl, brandDesc, lcl, PageRequest.of(page, size, this.sortByParam(sortBy)));		
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
  		Page<io.javabrains.springbootstarter.domain.Product> ppa = productPagingAndSortingRepository.findByCategoriesCategoryIdInAndAttributesLclCdAndPricesPriceValueBetweenAndPricesTypeDescAndPricesCurrencyCodeAndPricesStartDateLessThanAndPricesEndDateGreaterThan(categoryIds, lcl, new Double(0), price, "markdown", currency, new Date(), new Date(),PageRequest.of(page, size, this.sortByParam(sortBy)));
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
  		Page<io.javabrains.springbootstarter.domain.Product> ppa = productPagingAndSortingRepository.findByCategoriesCategoryIdInAndAttributesLclCdAndBrandBrandAttributesBrandDescAndBrandBrandAttributesLclCdAndPricesPriceValueBetweenAndPricesTypeDescAndPricesCurrencyCodeAndPricesStartDateLessThanAndPricesEndDateGreaterThan(categoryIds, lcl, brandDesc, lcl, new Double(0), price, "markdown", currency, new Date(), new Date(),PageRequest.of(page, size, this.sortByParam(sortBy)));
  		Page<Product> pp = ppa.map(pa -> this.convertToProductDto(pa, lcl, currency));
  		ResultContainer rc = new ResultContainer();
		rc.setProducts(pp);
		return rc;
	}

	@Cacheable
	public ResultContainer findProduct(String lcl, String currency, String categoryDesc, String searchTerm, int page, int size, String sortBy, io.javabrains.springbootstarter.facets.CategoryFacet[] selectedFacets) {

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
		
	
//		FacetingRequest brandFacetRequest = productQueryBuilder.facet()
//				.name("BrandDescFR")
//				.onField("brandDesc")
//				.discrete()
//				.orderedBy(FacetSortOrder.COUNT_DESC)
//				.includeZeroCounts(false)
//				.maxFacetCount(5)
//				.createFacetingRequest();
		
		

		
		//List<Facet> brandFacets = new ArrayList<Facet>();
		//we can add metadata to our facets by converting them to FacetDTOs and enriching with other attributes
		

		//facetMgr.enableFaceting(brandFacetRequest);
		//brandFacets.addAll(facetMgr.getFacets("BrandDescFR"));
		
//		filtering on a specific facet is as easy as..... 
//		FacetSelection facetSelection = facetMgr.getFacetGroup("BrandDescFR");
//		Facet facet = facets.stream().filter(f -> f.getValue().equals("Driscolls")).collect(Collectors.toList()).get(0);
//		facetSelection.selectFacets(facet);
		
//		facets.stream().forEach(f -> { 
//								System.out.println("Facet field = " +  f.getFieldName() + " value = " + f.getValue() + " - count = " + f.getCount());
//							});
//		
		
		//declare a list of facets
		List<Facet> categoryFacets = new ArrayList<Facet>(); 
		
		//create a category faceting request for the base level 
		FacetingRequest categoryFacetRequest = productQueryBuilder.facet()
				.name("CategoryDescFR")
				.onField("primaryCategory.categoryToken")
				.discrete()
				.orderedBy(FacetSortOrder.COUNT_DESC)
				.includeZeroCounts(false)
				.maxFacetCount(5)
				.createFacetingRequest();
		FacetManager facetMgr = jpaQuery.getFacetManager();
		facetMgr.enableFaceting(categoryFacetRequest);
		categoryFacets.addAll(facetMgr.getFacets("CategoryDescFR"));
		
		
		Pageable pageable = PageRequest.of(page, size);
		jpaQuery.setFirstResult(pageableUtil.getStartPosition(pageable));
		jpaQuery.setMaxResults(pageable.getPageSize());
			
		org.apache.lucene.search.Sort sort = new org.apache.lucene.search.Sort(new SortField(getSortField(sortBy), getSortFieldType(sortBy)));
		jpaQuery.setSort(sort);
		
		@SuppressWarnings("unchecked")
		List<ProductAttribute> results =  Collections.checkedList(jpaQuery.getResultList(), ProductAttribute.class);
	
		List<Product> lp = results.stream().map(pa -> this.convertToProductDto(pa.getProduct(), lcl, currency)).collect(Collectors.toList());
		
		Page<Product> pp = new PageImpl<Product>(lp, pageable, jpaQuery.getResultSize());
		
		//these need to be transformed into FacetDTOs
		ResultContainer src = new ResultContainer();
		
		Set<CategoryFacet> s = new HashSet<CategoryFacet>();
		
		categoryFacets.stream().forEach(cf ->  {
													String categoryCode = (new LinkedList<String>(Arrays.asList(cf.getValue().split("/")))).getLast();
													CategoryFacet c
														= convertToCategoryFacet(categoryCode, lcl, currency);
													c.setCount(new Long(cf.getCount()));
													c.setToken(cf.getValue());
													c.setName(cf.getFacetingName());
													c.setFieldName(cf.getFieldName());
													s.add(c);
											   });
		
		//now we have added the baseline categories to "s" we need to add the parents also and compute their facets
		//clone HashSet to resolve concurrency issues with recursion
		(new HashSet<CategoryFacet>(s)).stream().forEach(cf -> {
			setParentCategoryFacetCount(s, cf, productQueryBuilder, jpaQuery, lcl, currency, cf.getCategoryLevel());
		});
		
		src.setCategories(new ArrayList<CategoryFacet>(s));
		
		src.setProducts(pp);
		
		return src;
	}
	
    public CategoryFacet convertToCategoryFacet(String categoryCode, String lcl, String currency) {
    	Category c = productCategoryRepository.findByCategoryCode(categoryCode);
    	CategoryFacet cf = new CategoryFacet();
    	cf.setId(c.getCategoryId());
    	cf.setDesc(c.getAttributes().stream().filter(ca -> ca.getLclCd().equals(lcl)).collect(Collectors.toList()).get(0).getCategoryDesc());
    	cf.setCategoryLevel(c.getCategoryLevel());
    	if(c.getParent() != null) {
    		cf.setParentId(c.getParent().getCategoryId());
    	}
    	return cf;		
    }
    
    private void setParentCategoryFacetCount(Set<CategoryFacet> sc, CategoryFacet c, QueryBuilder qb, org.hibernate.search.jpa.FullTextQuery q, String lcl, String currency, Long baseLevel) {
    	if(c == null) { return; }
    	if(c.getParentId() == null) { return; }
    	
    	Category p = productCategoryRepository.findByCategoryId(c.getParentId());
    	CategoryFacet pcf = convertToCategoryFacet(p.getCategoryCode(), lcl, currency);
    	
    	String frName = "CategoryDescLvl" + p.getCategoryLevel() + "FR";
    	String frField = "primaryCategory" + StringUtils.repeat(".parent", baseLevel.intValue() - p.getCategoryLevel().intValue()) + ".categoryToken";
    		
    	FacetingRequest categoryFacetRequest = qb.facet()
    	.name(frName)
    	.onField(frField)
    	.discrete()
    	.orderedBy(FacetSortOrder.COUNT_DESC)
    	.includeZeroCounts(false)
    	.maxFacetCount(1)
    	.createFacetingRequest();
    		
    	FacetManager facetMgr = q.getFacetManager();
    	facetMgr.enableFaceting(categoryFacetRequest);
    	pcf.setCount(new Long(facetMgr.getFacets(frName).stream().collect(Collectors.toList()).get(0).getCount()));
    	pcf.setToken(String.join("/", Arrays.copyOfRange(c.getToken().split("/"), 0, c.getCategoryLevel().intValue()+1)));
		pcf.setName(facetMgr.getFacets(frName).stream().collect(Collectors.toList()).get(0).getFacetingName());
		pcf.setFieldName(facetMgr.getFacets(frName).stream().collect(Collectors.toList()).get(0).getFieldName());
    	sc.add(pcf);
    	this.setParentCategoryFacetCount(sc, pcf, qb, q, lcl, currency, baseLevel);
    }
    
    public Product convertToProductDto(final io.javabrains.springbootstarter.domain.Product product, String lcl, String currency) {
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
}