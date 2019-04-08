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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;

import io.javabrains.springbootstarter.domain.PageableUtil;
import io.javabrains.springbootstarter.domain.Product;
import io.javabrains.springbootstarter.domain.ProductAttribute;
import io.javabrains.springbootstarter.domain.ProductAttributeRepository;
import io.javabrains.springbootstarter.domain.ProductCategory;
import io.javabrains.springbootstarter.domain.ProductCategoryRepository;
import io.javabrains.springbootstarter.domain.ProductPagingAndSortingRepository;
import io.javabrains.springbootstarter.domain.ProductPriceRepository;
import io.javabrains.springbootstarter.domain.ProductRepository;

@Service
@Transactional
public class ProductDTOService implements IProductDTOService {

    @Autowired
    private ProductPagingAndSortingRepository productPagingAndSortingRepository;
    
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired ProductPriceRepository productPriceRepository;
    
    @Autowired
    private ProductAttributeRepository productAttributeRepository;
    
    @Autowired
    private ProductCategoryRepository productCategoryRepository;
    
	@PersistenceContext(unitName = "mochiEntityManagerFactory")
	private EntityManager em;
    
    // API
    //This method should accept a DTO and return a DTO
    //The DTO is coarse grained and contains a flat structure of properties
    //if we did not use a DTO we would have JSON nesting as per the domain model structure, which is hard to manage in our client views
    //The DTO is simple and dumb, it is the service layer that manages the translation between DTO and domain objects
    
    @Override
	@Transactional
	public Page<ProductDTO> getProducts(String lcl, String currency, int page, int size, String sortBy) {
    	Page<ProductDTO> pp;
		Page<Product> ppa = productPagingAndSortingRepository.findAll(PageRequest.of(page, size, this.sortByParam(sortBy)));
		pp = ppa.map(pa -> this.convertToProductDto(pa, lcl, currency));
		return pp;
	}	
    
    @Override
	@Transactional
	public ProductDTO getProduct(String lcl, String currency, Long id) {
		Product pa = productRepository.findById(id).get();
		ProductDTO p = this.convertToProductDto(pa, lcl, currency);
		return p;
	}	

    
    @Override
  	@Transactional
  	public List<ProductDTO> getPreviewProductsForCategory(String lcl, String currency, Long categoryId) {
     	List<ProductCategory> pcl = new ArrayList<ProductCategory>();
     	ProductCategory pc = productCategoryRepository.findByCategoryId(categoryId);
     	recurseCategories(pcl, pc);
     	List<Long> categoryIds = pcl.stream().map(sc -> sc.getCategoryId()).collect(Collectors.toList());
  		List<Product> ppa = productRepository.findByCategoriesCategoryIdIn(categoryIds);
  		List<ProductDTO> pp = ppa.stream().map(pa -> this.convertToProductDto(pa, lcl, currency)).collect(Collectors.toList());
  		return pp;
  	}	
    
	@Override
	public Page<ProductDTO> getProductsForCategory(String lcl, String currency, String categoryDesc, int page, int size, String sortBy) {
		List<ProductCategory> pcl = new ArrayList<ProductCategory>();
     	ProductCategory pc = productCategoryRepository.findByProductCategoryAttributeCategoryDesc(categoryDesc);
     	recurseCategories(pcl, pc);
     	List<Long> categoryIds = pcl.stream().map(sc -> sc.getCategoryId()).collect(Collectors.toList());
  		Page<Product> ppa = productPagingAndSortingRepository.findByCategoriesCategoryIdInAndAttributesLclCd(categoryIds, lcl, PageRequest.of(page, size, this.sortByParam(sortBy)));
  		Page<ProductDTO> pp = ppa.map(pa -> this.convertToProductDto(pa, lcl, currency));
  		return pp;
	}
	
	@Override
	public Page<ProductDTO> getProductsForCategory(String lcl, String currency, String categoryDesc, Long price, int page, int size, String sortBy) {
		System.out.println("getProductsForCategory");
		List<ProductCategory> pcl = new ArrayList<ProductCategory>();
     	ProductCategory pc = productCategoryRepository.findByProductCategoryAttributeCategoryDesc(categoryDesc);
     	recurseCategories(pcl, pc);
     	List<Long> categoryIds = pcl.stream().map(sc -> sc.getCategoryId()).collect(Collectors.toList());
  		Page<Product> ppa = productPagingAndSortingRepository.findByCategoriesCategoryIdInAndAttributesLclCdAndPricesPriceValueBetween(categoryIds, lcl, new Long(0), price, PageRequest.of(page, size, this.sortByParam(sortBy)));
  		Page<ProductDTO> pp = ppa.map(pa -> this.convertToProductDto(pa, lcl, currency));
  		return pp;
	}
	
	@Override
	public Page<ProductDTO> getProductsForCategoryAndBrand(String lcl, String currency, String categoryDesc, String brandDesc, int page, int size, String sortBy) {
		System.out.println("getProductsForCategoryAndBrand");
		List<ProductCategory> pcl = new ArrayList<ProductCategory>();
     	ProductCategory pc = productCategoryRepository.findByProductCategoryAttributeCategoryDesc(categoryDesc);
     	recurseCategories(pcl, pc);
     	List<Long> categoryIds = pcl.stream().map(sc -> sc.getCategoryId()).collect(Collectors.toList());
  		Page<Product> ppa = productPagingAndSortingRepository.findByCategoriesCategoryIdInAndAttributesLclCdAndBrandBrandAttributesBrandDescAndBrandBrandAttributesLclCd(categoryIds, lcl, brandDesc, lcl, PageRequest.of(page, size, this.sortByParam(sortBy)));		
  		Page<ProductDTO> pp = ppa.map(pa -> this.convertToProductDto(pa, lcl, currency));
  		return pp;
	}
	
	@Override
	public Page<ProductDTO> getProductsForCategoryAndPrice(String lcl, String currency, String categoryDesc, Long price, int page, int size, String sortBy) {
		System.out.println("getProductsForCategoryAndBrandAndPrice");
		List<ProductCategory> pcl = new ArrayList<ProductCategory>();
     	ProductCategory pc = productCategoryRepository.findByProductCategoryAttributeCategoryDesc(categoryDesc);
     	recurseCategories(pcl, pc);
     	List<Long> categoryIds = pcl.stream().map(sc -> sc.getCategoryId()).collect(Collectors.toList());
  		Page<Product> ppa = productPagingAndSortingRepository.findByCategoriesCategoryIdInAndAttributesLclCdAndPricesPriceValueBetweenAndPricesTypeDescAndPricesCurrencyCodeAndPricesStartDateLessThanAndPricesEndDateGreaterThan(categoryIds, lcl, new Long(0), price, "markdown", currency, new Date(), new Date(),PageRequest.of(page, size, this.sortByParam(sortBy)));
  		Page<ProductDTO> pp = ppa.map(pa -> this.convertToProductDto(pa, lcl, currency));
  		return pp;
	}

	
	@Override
	public Page<ProductDTO> getProductsForCategoryAndBrandAndPrice(String lcl, String currency, String categoryDesc, String brandDesc, Long price, int page, int size, String sortBy) {
		System.out.println("getProductsForCategoryAndBrandAndPrice");
		List<ProductCategory> pcl = new ArrayList<ProductCategory>();
     	ProductCategory pc = productCategoryRepository.findByProductCategoryAttributeCategoryDesc(categoryDesc);
     	recurseCategories(pcl, pc);
     	List<Long> categoryIds = pcl.stream().map(sc -> sc.getCategoryId()).collect(Collectors.toList());
  		Page<Product> ppa = productPagingAndSortingRepository.findByCategoriesCategoryIdInAndAttributesLclCdAndBrandBrandAttributesBrandDescAndBrandBrandAttributesLclCdAndPricesPriceValueBetweenAndPricesTypeDescAndPricesCurrencyCodeAndPricesStartDateLessThanAndPricesEndDateGreaterThan(categoryIds, lcl, brandDesc, lcl, new Long(0), price, "markdown", currency, new Date(), new Date(),PageRequest.of(page, size, this.sortByParam(sortBy)));
  		Page<ProductDTO> pp = ppa.map(pa -> this.convertToProductDto(pa, lcl, currency));
  		return pp;
	}

	public Page<ProductDTO> findProduct(String lcl, String currency, String categoryDesc, String searchTerm, int page, int size, String sortBy) {

		PageableUtil pageableUtil = new PageableUtil();
		
		FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(em);
				
		QueryBuilder productAttributeQueryBuilder = 
				fullTextEntityManager.getSearchFactory()
				  .buildQueryBuilder()
				  .forEntity(ProductAttribute.class)
				  .overridesForField("productDesc", lcl)
				  .overridesForField("categoryDesc", lcl)
				  .overridesForField("brandDesc", lcl)
				  .get();
		
		org.apache.lucene.search.Query searchQuery = productAttributeQueryBuilder.keyword()
													.onFields(
															"product.categories.parent.parent.productCategoryAttribute.categoryDesc",
															"product.categories.parent.productCategoryAttribute.categoryDesc",
															"product.categories.productCategoryAttribute.categoryDesc",
															"product.brand.brandAttributes.brandDesc",
															"productDesc"
													)
													.matching(searchTerm)
													 .createQuery();
				
		
		org.apache.lucene.search.Query query = 
			productAttributeQueryBuilder
			.bool()
			.must(searchQuery)
			.must(productAttributeQueryBuilder.keyword()
			.onFields(	"product.categories.productCategoryAttribute.lclCd",
						"product.categories.parent.productCategoryAttribute.lclCd",
						"product.categories.parent.parent.productCategoryAttribute.lclCd")
			.matching(lcl)
		    .createQuery())
			.must(productAttributeQueryBuilder.keyword()
			.onFields(	"product.categories.productCategoryAttribute.categoryDesc", 
						"product.categories.parent.productCategoryAttribute.categoryDesc",
						"product.categories.parent.parent.productCategoryAttribute.categoryDesc")
			.matching(categoryDesc)
			.createQuery())
			.must(productAttributeQueryBuilder.keyword()
			.onField("lclCd")
			.matching(lcl)
			.createQuery())
			.createQuery();
		
		
		org.hibernate.search.jpa.FullTextQuery jpaQuery
		  = fullTextEntityManager.createFullTextQuery(query, ProductAttribute.class);
		
		Pageable pageable = PageRequest.of(page, size);
		jpaQuery.setFirstResult(pageableUtil.getStartPosition(pageable));
		jpaQuery.setMaxResults(pageable.getPageSize());
	
		//sorting
		org.apache.lucene.search.Sort sort = new org.apache.lucene.search.Sort(new SortField(getSortField(sortBy), getSortFieldType(sortBy)));
		jpaQuery.setSort(sort);
		
		@SuppressWarnings("unchecked")
		List<Product> results =  Collections.checkedList(jpaQuery.getResultList(), Product.class);
		
		List<ProductDTO> lp = results.stream().map(pa -> this.convertToProductDto(pa, lcl, currency)).collect(Collectors.toList());

		Page<ProductDTO> pp = new PageImpl<ProductDTO>(lp, pageable, jpaQuery.getResultSize());
		return pp;
	}
	
	private String getSortField(String field) {
		switch(field) {
		case "description":
			return "productDesc";
		case "price":
			return "productRrp";
		case "productDesc":
			return "productDesc";
		case "productRrp":
			return "productRrp";
		default: 
			return "productDesc";
		}
	}
	
	private SortField.Type getSortFieldType(String field) {
		switch(field) {
		case "productDesc":
			return SortField.Type.STRING;
		case "description":
			return SortField.Type.STRING;
		case "price":
			return SortField.Type.DOUBLE;
		case "productRrp":
			return SortField.Type.DOUBLE;
		default: 
			return SortField.Type.STRING;
		}
	}
    
    public void recurseCategories(List<ProductCategory> pcl, ProductCategory pc) {
    	pcl.add(pc);
    	pc.getChildren().forEach(child -> recurseCategories(pcl, child));
    }
    
	
    public ProductDTO convertToProductDto(final Product product, String lcl, String currency) {
        //get values from contact entity and set them in contactDto
        //e.g. contactDto.setContactId(contact.getContactId());
    	
    	ProductAttribute pa = productAttributeRepository.findByLclCdAndProductId(lcl, product.getProductId());
    	
        final ProductDTO productDto = new ProductDTO();
        productDto.setProductId(product.getProductId());
        productDto.setProductCreateDt(product.getProductCreateDt());
        productDto.setProductUPC(product.getProductUPC());
        productDto.setProductDesc(pa.getProductDesc());
        
        /*instead of filtering the stream use JPA queries*/
        //productDto.setProductRetail(productAttribute.getProduct().getProductPrices().stream().filter(p -> p.getPriceCurrency().getCurrencyCode().equals(currency) && p.getPriceType().getPriceTypeDesc().equals("retail")).collect(Collectors.toList()).get(0).getPriceValue());
        //productDto.setProductMarkdown(productAttribute.getProduct().getProductPrices().stream().filter(p -> p.getPriceCurrency().getCurrencyCode().equals(currency) && p.getPriceType().getPriceTypeDesc().equals("markdown")).collect(Collectors.toList()).get(0).getPriceValue());
                
        productDto.setProductRetail(productPriceRepository.findByProductProductIdAndTypeDescAndStartDateLessThanEqualAndEndDateGreaterThanEqualAndCurrencyCode(product.getProductId(), "retail", new Date(), new Date(), currency).getPriceValue());
        productDto.setProductMarkdown(productPriceRepository.findByProductProductIdAndTypeDescAndStartDateLessThanEqualAndEndDateGreaterThanEqualAndCurrencyCode(product.getProductId(), "markdown", new Date(), new Date(), currency).getPriceValue());
        
        productDto.setProductImage(pa.getProductImage());
        productDto.setLclCd(lcl);
        productDto.setBrandDesc(product.getBrand().getBrandAttributes().stream()
        .filter( ba -> ba.getLclCd().equals(lcl)).collect(Collectors.toList()).get(0).getbrandDesc());
        return productDto;
    }
    
    private Sort sortByParam(String param) {
    	switch (param) {
    	case "priceAsc": return new Sort(Sort.Direction.ASC, "Prices.PriceValue");
    	case "priceDesc": return new Sort(Sort.Direction.DESC, "Prices.PriceValue");
    	case "nameAsc": return new Sort(Sort.Direction.ASC, "Attributes.productDesc");
    	default: return new Sort(Sort.Direction.ASC, "Attributes.productDesc");
    	}
    }

}