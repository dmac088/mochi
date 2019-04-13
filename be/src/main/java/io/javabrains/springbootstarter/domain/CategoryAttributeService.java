package io.javabrains.springbootstarter.domain;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryAttributeService {

	@Autowired
	private CategoryAttributeRepository productCategoryAttributeRepository; 
	
	
	public List<CategoryAttribute> getAllProductCategoryAttributes(String lcl) {
		return productCategoryAttributeRepository.findByLclCd(lcl);
	}
	
	public Optional<CategoryAttribute> getProductCategoryAttribute(String lcl, Long id) {
		return productCategoryAttributeRepository.findByLclCdAndCategoryId(lcl, id);
	}
	
	public void addProductCategory(CategoryAttribute productCategoryAttribute) {
		productCategoryAttributeRepository.save(productCategoryAttribute);
	}
	
	public void updateProductCategory(Long id, CategoryAttribute productCategoryAttribute) {
		productCategoryAttributeRepository.save(productCategoryAttribute);
	}
	
	public void deleteProductCategory(Long id) {
		productCategoryAttributeRepository.deleteById(id);
	}
	
}
