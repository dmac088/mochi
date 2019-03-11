package io.javabrains.springbootstarter.services;


import java.util.List;

import org.springframework.data.domain.Page;

import io.javabrains.springbootstarter.domain.ProductCategory;


public interface IProductDTOService {
	
	 Page<ProductDTO> getProducts(String lcl, int page, int size, String sortBy);
	 
	 Page<ProductDTO> getProductsForCategory(String lcl, Long categoryId, int page, int size, String sortBy);
	 
	 Page<ProductDTO> getAllProductsForCategory(String lcl, Long categoryId, int page, int size, String sortBy);
	 
	 List<ProductDTO> getPreviewProductsForCategory(String lcl, Long categoryId);
	 
	 List<ProductDTO> getFeaturedProducts(String lcl);
	 
	 public void recurseCategories(List<ProductCategory> pcl, ProductCategory pc);
	 
}
