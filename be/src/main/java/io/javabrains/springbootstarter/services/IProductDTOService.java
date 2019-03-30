package io.javabrains.springbootstarter.services;


import java.util.List;

import org.springframework.data.domain.Page;
import io.javabrains.springbootstarter.domain.ProductCategory;


public interface IProductDTOService {
	
	 Page<ProductDTO> getProducts(String lcl, int page, int size, String sortBy);
	 
	 public ProductDTO getProduct(String lcl, Long id);
	 
	 Page<ProductDTO> getProductsForCategory(String lcl, String categoryDesc, int page, int size, String sortBy);
	 
	 Page<ProductDTO> getProductsForCategoryAndBrand(String lcl, String categoryDesc, String brandDesc, int page, int size, String sortBy);
	 
	 List<ProductDTO> getPreviewProductsForCategory(String lcl, Long categoryId);
	 
	 public void recurseCategories(List<ProductCategory> pcl, ProductCategory pc);
	 
}
