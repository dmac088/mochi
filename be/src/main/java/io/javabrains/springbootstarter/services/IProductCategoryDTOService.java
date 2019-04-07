package io.javabrains.springbootstarter.services;


import java.util.List;

public interface IProductCategoryDTOService {
	 
	 List<ProductCategoryDTO> getProductCategories(String lcl, String currency);
	 
	 List<ProductCategoryDTO> getProductCategoriesForLevel(String lcl, String currency, Long level);
	 
	 List<ProductCategoryDTO> getProductCategoryParent(String lcl, String currency, Long parentCategoryId);
	 
	 List<ProductCategoryDTO> getPreviewProductCategories(final String lcl, String currency, final Long previewFlag);
	 
	 ProductCategoryDTO getProductCategory(String lcl, String currency, Long categoryId);
	 
	 ProductCategoryDTO getProductCategory(String lcl, String currency, String categoryDesc);

}
