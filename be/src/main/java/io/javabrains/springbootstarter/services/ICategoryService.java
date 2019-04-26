package io.javabrains.springbootstarter.services;


import java.util.List;

import io.javabrains.springbootstarter.domain.Category;

public interface ICategoryService {
	 
	 List<Category> getCategories(String lcl, String currency);
	 
	 List<Category> getCategoriesForLevel(String lcl, String currency, Long level);
	 
	 List<Category> getCategoryParent(String lcl, String currency, Long parentCategoryId);
	 
	 Category getCategory(String lcl, String currency, Long categoryId);
	 
	 Category getCategory(String lcl, String currency, String categoryDesc);

}
