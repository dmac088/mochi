package io.javabrains.springbootstarter.services;


import java.util.List;

public interface ICategoryDTOService {
	 
	 List<CategoryDTO> getCategories(String lcl, String currency);
	 
	 List<CategoryDTO> getCategoriesForLevel(String lcl, String currency, Long level);
	 
	 List<CategoryDTO> getCategoryParent(String lcl, String currency, Long parentCategoryId);
	 
	 CategoryDTO getCategory(String lcl, String currency, Long categoryId);
	 
	 CategoryDTO getCategory(String lcl, String currency, String categoryDesc);

}
