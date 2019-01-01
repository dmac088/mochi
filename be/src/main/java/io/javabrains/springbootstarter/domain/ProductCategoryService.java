package io.javabrains.springbootstarter.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductCategoryService {

	@Autowired
	private ProductCategoryRepository productCategoryRepository; 
	
	public List<ProductCategory> getAllProductCategories(String lcl) {
		List<ProductCategory> categories = new ArrayList<>();
		Iterator<ProductCategory> i = productCategoryRepository.findByLclCd(lcl).iterator();
		while(i.hasNext()) {
			categories.add(i.next());
		}
		return categories;
	}
	

	
	public Optional<ProductCategory> getProductCategory(Long id) {
		Optional<ProductCategory> p = productCategoryRepository.findById(id);
		return p;
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
