package io.javabrains.springbootstarter.services;


import java.util.List;

public interface IProductCategoryDTOService {
	 
	 List<ProductCategoryDTO> getProductCategories(String lcl);
	 
	 List<ProductCategoryDTO> getProductCategories(String lcl, Long parentCategoryId);

}
