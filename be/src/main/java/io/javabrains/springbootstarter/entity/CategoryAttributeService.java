package io.javabrains.springbootstarter.entity;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import variables.CategoryVars;

@Service
public class CategoryAttributeService {

	@Autowired
	private CategoryAttributeRepository categoryAttributeRepository; 
	
	
	public List<CategoryAttribute> getAllCategoryAttributes(String lcl) {
		return categoryAttributeRepository.findByLclCd(lcl);
	}
	
	public CategoryAttribute getCategoryAttribute(String lcl, Long id) {
		return categoryAttributeRepository.findByCategoryHierarchyCodeAndLclCdAndCategoryId(CategoryVars.PRIMARY_HIERARCHY_CODE,lcl, id);
	}
	
	public void addCategory(CategoryAttribute CategoryAttribute) {
		categoryAttributeRepository.save(CategoryAttribute);
	}
	
	public void updateCategory(Long id, CategoryAttribute CategoryAttribute) {
		categoryAttributeRepository.save(CategoryAttribute);
	}
	
	public void deleteCategory(Long id) {
		categoryAttributeRepository.deleteById(id);
	}
	
}
