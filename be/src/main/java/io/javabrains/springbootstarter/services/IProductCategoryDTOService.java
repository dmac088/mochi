package io.javabrains.springbootstarter.services;


import java.util.List;

public interface IProductCategoryDTOService {
	 
	 List<ProductCategoryDTO> getProductCategories(String lcl);
	 
	 List<ProductCategoryDTO> getProductCategoriesForLevel(String lcl, Long level);
	 
	 List<ProductCategoryDTO> getProductCategoryParent(String lcl, Long parentCategoryId);
	 
	 List<ProductCategoryDTO> getPreviewProductCategories(final String lcl, final Long previewFlag);
	 
	 ProductCategoryDTO getProductCategory(String lcl, Long categoryId);
	 
	 ProductCategoryDTO getProductCategory(String lcl, String categoryDesc);

}
