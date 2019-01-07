package io.javabrains.springbootstarter.services;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import io.javabrains.springbootstarter.domain.ProductAttribute;
import io.javabrains.springbootstarter.domain.ProductAttributePagingAndSortingRepository;

@Service
@Transactional
public class ProductDTOService implements IProductDTOService {

    @Autowired
    private ProductAttributePagingAndSortingRepository productAttributePagingAndSortingRepository;
    
    // API
    //This method should accept a DTO and return a DTO
    //The DTO is coarse grained and contains a flat structure of properties
    //if we did not use a DTO we would have JSON nesting as per the domain model structure, which is hard to manage in our client views
    //The DTO is simple and dumb, it is the service layer that manages the translation between DTO and domain objects
    
    @Override
	@Transactional
	public Page<ProductDTO> getProducts(String lcl, int page, int size) {
		Page<ProductAttribute> ppa = productAttributePagingAndSortingRepository.findByLclCd(lcl, PageRequest.of(page, size, Sort.by("productRrp").descending()));
		Page<ProductDTO> pp = ppa.map(this::convertToProductDto);
		return pp;
	}	
    
    @Override
 	@Transactional
 	public Page<ProductDTO> getProducts(String lcl, Long categoryId, int page, int size) {
 		Page<ProductAttribute> ppa = productAttributePagingAndSortingRepository.findByLclCdAndProductCategoriesCategoryId(lcl, categoryId, PageRequest.of(page, size, Sort.by("productRrp").descending()));
 		Page<ProductDTO> pp = ppa.map(this::convertToProductDto);
 		return pp;
 	}	
	
    private ProductDTO convertToProductDto(final ProductAttribute productAttribute) {
        //get values from contact entity and set them in contactDto
        //e.g. contactDto.setContactId(contact.getContactId());
        final ProductDTO productDto = new ProductDTO();
        productDto.setProductId(productAttribute.getProduct().getProductId());
        productDto.setProductCreateDt(productAttribute.getProduct().getProductCreateDt());
        productDto.setProductUPC(productAttribute.getProduct().getProductUPC());
        productDto.setProductDesc(productAttribute.getProductDesc());
        productDto.setProductRrp(productAttribute.getProductRrp());
        productDto.setProductImage(productAttribute.getProductImage());
        productDto.setLclCd(productAttribute.getLclCd());
        return productDto;
    }
}