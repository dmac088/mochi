package io.javabrains.springbootstarter.services;


import java.util.List;

import io.javabrains.springbootstarter.domain.Category;
import io.javabrains.springbootstarter.dto.SidebarFacetDTO;

public interface ICategoryService {
	 
	List<Category> getCategories(String lcl, String currency);
	 
	List<Category> getCategoriesForLevel(String lcl, String currency, Long level);
	 
	List<Category> getCategoryParent(String lcl, String currency, Long parentCategoryId);
	 
	Category getCategory(String lcl, String currency, Long categoryId);
	 
	Category getCategory(String lcl, String currency, String categoryDesc);

	List<SidebarFacetDTO> getCategoryChildren(String lcl, String currency, String categoryDesc);

}
