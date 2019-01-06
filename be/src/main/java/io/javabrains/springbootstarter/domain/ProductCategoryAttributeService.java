package io.javabrains.springbootstarter.domain;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductCategoryAttributeService {

	@Autowired
	private ProductCategoryAttributeRepository productCategoryAttributeRepository; 
	
	
	public List<ProductCategoryAttribute> getAllProductCategoryAttributes(String lcl) {
		return productCategoryAttributeRepository.findAllByLclCd(lcl);
	}
	
	public Optional<ProductCategoryAttribute> getProductCategoryAttribute(String lcl, Long id) {
		return productCategoryAttributeRepository.findByLclCdAndCategoryId(lcl, id);
	}
	
	public void addProductCategory(ProductCategoryAttribute productCategoryAttribute) {
		productCategoryAttributeRepository.save(productCategoryAttribute);
	}
	
	public void updateProductCategory(Long id, ProductCategoryAttribute productCategoryAttribute) {
		productCategoryAttributeRepository.save(productCategoryAttribute);
	}
	
	public void deleteProductCategory(Long id) {
		productCategoryAttributeRepository.deleteById(id);
	}
	
}
