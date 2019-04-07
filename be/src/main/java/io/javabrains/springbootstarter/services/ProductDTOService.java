package io.javabrains.springbootstarter.services;

import java.util.ArrayList;
import java.util.Collection;
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
import io.javabrains.springbootstarter.domain.ProductAttribute;
import io.javabrains.springbootstarter.domain.ProductAttributePagingAndSortingRepository;
import io.javabrains.springbootstarter.domain.ProductAttributeRepository;
import io.javabrains.springbootstarter.domain.ProductCategory;
import io.javabrains.springbootstarter.domain.ProductCategoryRepository;
import io.javabrains.springbootstarter.domain.ProductPriceRepository;

@Service
@Transactional
public class ProductDTOService implements IProductDTOService {

    @Autowired
    private ProductAttributePagingAndSortingRepository productAttributePagingAndSortingRepository;
    
    @Autowired
    private ProductCategoryRepository productCategoryRepository;
    
    @Autowired
    private ProductAttributeRepository productAttributeRepository;
    
    @Autowired
    private ProductPriceRepository productPriceRepository;
    
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
		Page<ProductAttribute> ppa = productAttributePagingAndSortingRepository.findByLclCd(lcl, PageRequest.of(page, size, this.sortByParam(sortBy)));
		pp = ppa.map(pa -> this.convertToProductDto(pa, lcl, currency));
		return pp;
	}	
    
    @Override
	@Transactional
	public ProductDTO getProduct(String lcl, String currency, Long id) {
		ProductAttribute pa = productAttributeRepository.findByLclCdAndProductId(lcl, id).get();
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
  		Collection<ProductAttribute> ppa = productAttributeRepository.findDistinctByLclCdAndProductCategoriesCategoryIdInAndProductPreviewFlag(lcl, categoryIds, new Long(1));
  		List<ProductDTO> pp = ppa.stream().map(pa -> this.convertToProductDto(pa, lcl, currency)).collect(Collectors.toList());
  		return pp;
  	}	
    
	@Override
	public Page<ProductDTO> getProductsForCategory(String lcl, String currency, String categoryDesc, int page, int size, String sortBy) {
		List<ProductCategory> pcl = new ArrayList<ProductCategory>();
     	ProductCategory pc = productCategoryRepository.findByProductCategoryAttributeLclCdAndProductCategoryAttributeCategoryDesc(lcl, categoryDesc);
     	recurseCategories(pcl, pc);
     	List<Long> categoryIds = pcl.stream().map(sc -> sc.getCategoryId()).collect(Collectors.toList());
  		Page<ProductAttribute> ppa = productAttributePagingAndSortingRepository.findDistinctByLclCdAndProductCategoriesCategoryIdIn(lcl, categoryIds, PageRequest.of(page, size, this.sortByParam(sortBy)));
  		Page<ProductDTO> pp = ppa.map(pa -> this.convertToProductDto(pa, lcl, currency));
  		return pp;
	}
	
	@Override
	public Page<ProductDTO> getProductsForCategory(String lcl, String currency, String categoryDesc, Long price, int page, int size, String sortBy) {
		List<ProductCategory> pcl = new ArrayList<ProductCategory>();
     	ProductCategory pc = productCategoryRepository.findByProductCategoryAttributeLclCdAndProductCategoryAttributeCategoryDesc(lcl, categoryDesc);
     	recurseCategories(pcl, pc);
     	List<Long> categoryIds = pcl.stream().map(sc -> sc.getCategoryId()).collect(Collectors.toList());
  		Page<ProductAttribute> ppa = productAttributePagingAndSortingRepository.findDistinctByLclCdAndProductCategoriesCategoryIdInAndProductPricesPriceValueBetween(lcl, categoryIds, new Long(0), price, PageRequest.of(page, size, this.sortByParam(sortBy)));
  		Page<ProductDTO> pp = ppa.map(pa -> this.convertToProductDto(pa, lcl, currency));
  		return pp;
	}
	
	@Override
	public Page<ProductDTO> getProductsForCategoryAndBrand(String lcl, String currency, String categoryDesc, String brandDesc, int page, int size, String sortBy) {
		List<ProductCategory> pcl = new ArrayList<ProductCategory>();
     	ProductCategory pc = productCategoryRepository.findByProductCategoryAttributeLclCdAndProductCategoryAttributeCategoryDesc(lcl, categoryDesc);
     	recurseCategories(pcl, pc);
     	List<Long> categoryIds = pcl.stream().map(sc -> sc.getCategoryId()).collect(Collectors.toList());
  		Page<ProductAttribute> ppa = productAttributePagingAndSortingRepository.findDistinctByLclCdAndProductCategoriesCategoryIdInAndProductBrandBrandAttributesBrandDesc(lcl, categoryIds, brandDesc,PageRequest.of(page, size, this.sortByParam(sortBy)));
  		Page<ProductDTO> pp = ppa.map(pa -> this.convertToProductDto(pa, lcl, currency));
  		return pp;
	}
	
	@Override
	public Page<ProductDTO> getProductsForCategoryAndBrandAndPrice(String lcl, String currency, String categoryDesc, String brandDesc, Long price, int page, int size, String sortBy) {
		List<ProductCategory> pcl = new ArrayList<ProductCategory>();
     	ProductCategory pc = productCategoryRepository.findByProductCategoryAttributeLclCdAndProductCategoryAttributeCategoryDesc(lcl, categoryDesc);
     	recurseCategories(pcl, pc);
     	List<Long> categoryIds = pcl.stream().map(sc -> sc.getCategoryId()).collect(Collectors.toList());
  		Page<ProductAttribute> ppa = productAttributePagingAndSortingRepository.findDistinctByLclCdAndProductCategoriesCategoryIdInAndProductBrandBrandAttributesBrandDescAndProductPricesPriceValueBetween(lcl, categoryIds, brandDesc, new Long(0), price, PageRequest.of(page, size, this.sortByParam(sortBy)));
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
		List<ProductAttribute> results =  Collections.checkedList(jpaQuery.getResultList(), ProductAttribute.class);
		
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
    
	
    public ProductDTO convertToProductDto(final ProductAttribute productAttribute, String lcl, String currency) {
        //get values from contact entity and set them in contactDto
        //e.g. contactDto.setContactId(contact.getContactId());
        final ProductDTO productDto = new ProductDTO();
        productDto.setProductId(productAttribute.getProduct().getProductId());
        productDto.setProductCreateDt(productAttribute.getProduct().getProductCreateDt());
        productDto.setProductUPC(productAttribute.getProduct().getProductUPC());
        productDto.setProductDesc(productAttribute.getProductDesc());
        
        /*instead of filtering the stream use JPA queries*/
        //productDto.setProductRetail(productAttribute.getProduct().getProductPrices().stream().filter(p -> p.getPriceCurrency().getCurrencyCode().equals(currency) && p.getPriceType().getPriceTypeDesc().equals("retail")).collect(Collectors.toList()).get(0).getPriceValue());
        //productDto.setProductMarkdown(productAttribute.getProduct().getProductPrices().stream().filter(p -> p.getPriceCurrency().getCurrencyCode().equals(currency) && p.getPriceType().getPriceTypeDesc().equals("markdown")).collect(Collectors.toList()).get(0).getPriceValue());
                
        productDto.setProductRetail(productPriceRepository.findByProductProductIdAndPriceTypePriceTypeDescAndPriceStartDateLessThanEqualAndPriceEndDateGreaterThanEqualAndPriceCurrencyCurrencyCode(productDto.getProductId(), "retail", new Date(), new Date(), currency).getPriceValue());
        productDto.setProductMarkdown(productPriceRepository.findByProductProductIdAndPriceTypePriceTypeDescAndPriceStartDateLessThanEqualAndPriceEndDateGreaterThanEqualAndPriceCurrencyCurrencyCode(productDto.getProductId(), "markdown", new Date(), new Date(), currency).getPriceValue());
        
        productDto.setProductImage(productAttribute.getProductImage());
        productDto.setLclCd(lcl);
        productDto.setBrandDesc(productAttribute.getProduct().getBrand().getBrandAttributes().stream()
        .filter( ba -> ba.getLclCd().equals(lcl)).collect(Collectors.toList()).get(0).getbrandDesc());
        return productDto;
    }
    
    private Sort sortByParam(String param) {
    	switch (param) {
    	case "priceAsc": return new Sort(Sort.Direction.ASC, "productRrp");
    	case "priceDesc": return new Sort(Sort.Direction.DESC, "productRrp");
    	case "nameAsc": return new Sort(Sort.Direction.ASC, "productDesc");
    	default: return new Sort(Sort.Direction.ASC, "productDesc");
    	}
    }

}