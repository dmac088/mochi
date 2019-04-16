package io.javabrains.springbootstarter.services;


import java.util.List;

import org.springframework.data.domain.Page;
import io.javabrains.springbootstarter.domain.Category;


public interface IProductService {
	
	 Page<Product> getProducts(String lcl, String currency, int page, int size, String sortBy);
	 
	 public Product getProduct(String lcl, String currency, Long id);
	 
	 Page<Product> getProductsForCategory(String lcl, String currency, String categoryDesc, int page, int size, String sortBy);
	 
	 Page<Product> getProductsForCategory(String lcl, String currency, String categoryDesc, Long price, int page, int size, String sortBy);
	 
	 Page<Product> getProductsForCategoryAndBrand(String lcl, String currency, String categoryDesc, String brandDesc, int page, int size, String sortBy);
	 
	 Page<Product> getProductsForCategoryAndPrice(String lcl, String currency, String categoryDesc, Long price, int page, int size, String sortBy);
	 
	 Page<Product> getProductsForCategoryAndBrandAndPrice(String lcl, String currency, String categoryDesc, String brandDesc, Long price, int page, int size, String sortBy);
	 
	 Page<Product> findProduct(String lcl, String currency, String categoryCode, String term, int page, int size, String sortBy);
	 
	 List<Product> getPreviewProductsForCategory(String lcl, String currency, Long categoryId);
	 
	 public void recurseCategories(List<Category> pcl, Category pc);
	 
}
