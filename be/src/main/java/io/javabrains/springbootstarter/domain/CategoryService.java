package io.javabrains.springbootstarter.domain;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository productCategoryRepository; 
	
	
	public List<Category> getAllProductCategories() {
		return productCategoryRepository.findAll();
	}
	
	public Category getProductCategory(Long id) {
		return productCategoryRepository.findByCategoryId(id);
	}
	
	public void addProductCategory(Category productCategory) {
		productCategoryRepository.save(productCategory);
	}
	
	public void updateProductCategory(Long id, Category productCategory) {
		productCategoryRepository.save(productCategory);
	}
	
	public void deleteProductCategory(Long id) {
		productCategoryRepository.deleteById(id);
	}
	
}
