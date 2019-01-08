package io.javabrains.springbootstarter.services;


import java.util.List;

public interface IProductCategoryDTOService {
	 
	 List<ProductCategoryDTO> getProductCategories(String lcl);
	 
	 List<ProductCategoryDTO> getProductCategoriesForLevel(String lcl, Long level);
	 
	 List<ProductCategoryDTO> getProductCategories(String lcl, Long parentCategoryId);
	 
	 ProductCategoryDTO getProductCategory(String lcl, Long categoryId);

}
