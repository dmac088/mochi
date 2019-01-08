package io.javabrains.springbootstarter.services;


import java.util.List;

import org.springframework.data.domain.Page;

import io.javabrains.springbootstarter.domain.ProductCategory;


public interface IProductDTOService {
	
	 Page<ProductDTO> getProducts(String lcl, int page, int size);
	 
	 Page<ProductDTO> getProductsForCategory(String lcl, Long categoryId, int page, int size);
	 
	 Page<ProductDTO> getAllProductsForCategory(String lcl, Long categoryId, int page, int size);
	 
	 public void recurseCategories(List<ProductCategory> pcl, ProductCategory pc);
	 
}
