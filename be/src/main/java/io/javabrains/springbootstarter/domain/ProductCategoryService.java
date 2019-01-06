package io.javabrains.springbootstarter.domain;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductCategoryService {

	@Autowired
	private ProductCategoryRepository productCategoryRepository; 
	
	public List<ProductCategory> getAllProductCategories(String lcl) {
		return productCategoryRepository.findAll();
	}
	
	public void addProductCategory(ProductCategory productCategory) {
		productCategoryRepository.save(productCategory);
	}
	
	public void updateProductCategory(Long id, ProductCategory productCategory) {
		productCategoryRepository.save(productCategory);
	}
	
	public void deleteProductCategory(Long id) {
		productCategoryRepository.deleteById(id);
	}
	
}
