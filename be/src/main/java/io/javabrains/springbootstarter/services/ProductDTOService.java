package io.javabrains.springbootstarter.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import io.javabrains.springbootstarter.domain.ProductAttribute;
import io.javabrains.springbootstarter.domain.ProductAttributePagingAndSortingRepository;
import io.javabrains.springbootstarter.domain.ProductAttributeRepository;
import io.javabrains.springbootstarter.domain.ProductCategory;
import io.javabrains.springbootstarter.domain.ProductCategoryRepository;

@Service
@Transactional
public class ProductDTOService implements IProductDTOService {

    @Autowired
    private ProductAttributePagingAndSortingRepository productAttributePagingAndSortingRepository;
    
    @Autowired
    private ProductCategoryRepository productCategoryRepository;
    
    @Autowired
    private ProductAttributeRepository productAttributeRepository;
    
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
		pp = ppa.map(pa -> ProductDTOService.convertToProductDto(pa, currency));
		return pp;
	}	
    
    @Override
	@Transactional
	public ProductDTO getProduct(String lcl, String currency, Long id) {
		ProductAttribute pa = productAttributeRepository.findByLclCdAndProductId(lcl, id).get();
		ProductDTO p = ProductDTOService.convertToProductDto(pa, currency);
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
  		List<ProductDTO> pp = ppa.stream().map(pa -> ProductDTOService.convertToProductDto(pa, currency)).collect(Collectors.toList());
  		return pp;
  	}	
    
	@Override
	public Page<ProductDTO> getProductsForCategory(String lcl, String currency, String categoryDesc, int page, int size, String sortBy) {
		List<ProductCategory> pcl = new ArrayList<ProductCategory>();
     	ProductCategory pc = productCategoryRepository.findByProductCategoryAttributeLclCdAndProductCategoryAttributeCategoryDesc(lcl, categoryDesc);
     	recurseCategories(pcl, pc);
     	List<Long> categoryIds = pcl.stream().map(sc -> sc.getCategoryId()).collect(Collectors.toList());
  		Page<ProductAttribute> ppa = productAttributePagingAndSortingRepository.findDistinctByLclCdAndProductCategoriesCategoryIdIn(lcl, categoryIds, PageRequest.of(page, size, this.sortByParam(sortBy)));
  		Page<ProductDTO> pp = ppa.map(pa -> ProductDTOService.convertToProductDto(pa, currency));
  		return pp;
	}
	
	@Override
	public Page<ProductDTO> getProductsForCategoryAndBrand(String lcl, String categoryDesc, String currency, String brandDesc, int page, int size, String sortBy) {
		List<ProductCategory> pcl = new ArrayList<ProductCategory>();
     	ProductCategory pc = productCategoryRepository.findByProductCategoryAttributeLclCdAndProductCategoryAttributeCategoryDesc(lcl, categoryDesc);
     	recurseCategories(pcl, pc);
     	List<Long> categoryIds = pcl.stream().map(sc -> sc.getCategoryId()).collect(Collectors.toList());
  		Page<ProductAttribute> ppa = productAttributePagingAndSortingRepository.findDistinctByLclCdAndProductCategoriesCategoryIdInAndProductBrandBrandAttributesBrandDesc(lcl, categoryIds, brandDesc,PageRequest.of(page, size, this.sortByParam(sortBy)));
  		Page<ProductDTO> pp = ppa.map(pa -> ProductDTOService.convertToProductDto(pa, currency));
  		return pp;
	}

    
    public void recurseCategories(List<ProductCategory> pcl, ProductCategory pc) {
    	pcl.add(pc);
    	for(ProductCategory child : pc.getChildren()) {
    		recurseCategories(pcl, child);
    	}
    }
    
	
    public static ProductDTO convertToProductDto(final ProductAttribute productAttribute, String currency) {
        //get values from contact entity and set them in contactDto
        //e.g. contactDto.setContactId(contact.getContactId());
        final ProductDTO productDto = new ProductDTO();
        productDto.setProductId(productAttribute.getProduct().getProductId());
        productDto.setProductCreateDt(productAttribute.getProduct().getProductCreateDt());
        productDto.setProductUPC(productAttribute.getProduct().getProductUPC());
        productDto.setProductDesc(productAttribute.getProductDesc());
        productDto.setProductRetail(productAttribute.getProduct().getProductPrices().stream().filter(p -> p.getPriceCurrency().getCurrencyCode().equals(currency) && p.getPriceType().getPriceTypeDesc().equals("retail")).collect(Collectors.toList()).get(0).getPriceValue());
        productDto.setProductMarkdown(productAttribute.getProduct().getProductPrices().stream().filter(p -> p.getPriceCurrency().getCurrencyCode().equals(currency) && p.getPriceType().getPriceTypeDesc().equals("markdown")).collect(Collectors.toList()).get(0).getPriceValue());
        productDto.setProductImage(productAttribute.getProductImage());
        productDto.setLclCd(productAttribute.getLclCd());
        productDto.setBrandDesc(productAttribute.getProduct().getBrand().getBrandAttributes().stream()
        .filter( ba -> ba.getLclCd().equals(productAttribute.getLclCd())).collect(Collectors.toList()).get(0).getbrandDesc());
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