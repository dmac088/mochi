package io.javabrains.springbootstarter.services;


import java.util.List;

import org.springframework.data.domain.Page;
import io.javabrains.springbootstarter.domain.Category;


public interface IProductDTOService {
	
	 Page<ProductDTO> getProducts(String lcl, String currency, int page, int size, String sortBy);
	 
	 public ProductDTO getProduct(String lcl, String currency, Long id);
	 
	 Page<ProductDTO> getProductsForCategory(String lcl, String currency, String categoryDesc, int page, int size, String sortBy);
	 
	 Page<ProductDTO> getProductsForCategory(String lcl, String currency, String categoryDesc, Long price, int page, int size, String sortBy);
	 
	 Page<ProductDTO> getProductsForCategoryAndBrand(String lcl, String currency, String categoryDesc, String brandDesc, int page, int size, String sortBy);
	 
	 Page<ProductDTO> getProductsForCategoryAndPrice(String lcl, String currency, String categoryDesc, Long price, int page, int size, String sortBy);
	 
	 Page<ProductDTO> getProductsForCategoryAndBrandAndPrice(String lcl, String currency, String categoryDesc, String brandDesc, Long price, int page, int size, String sortBy);
	 
	 Page<ProductDTO> findProduct(String lcl, String currency, String categoryCode, String term, int page, int size, String sortBy);
	 
	 List<ProductDTO> getPreviewProductsForCategory(String lcl, String currency, Long categoryId);
	 
	 public void recurseCategories(List<Category> pcl, Category pc);
	 
}
