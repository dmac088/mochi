package io.javabrains.springbootstarter.services;


import java.util.List;
import io.javabrains.springbootstarter.domain.Category;
import io.javabrains.springbootstarter.facets.CategoryFacet;

public interface IProductService {
	
	 ResultContainer getProducts(String lcl, String currency, int page, int size, String sortBy);
	 
	 public Product getProduct(String lcl, String currency, Long id);
	 
	 ResultContainer getProductsForCategory(String lcl, String currency, String categoryDesc, int page, int size, String sortBy);
	 
	 ResultContainer getProductsForCategory(String lcl, String currency, String categoryDesc, Double price, int page, int size, String sortBy);
	 
	 ResultContainer getProductsForCategoryAndBrand(String lcl, String currency, String categoryDesc, String brandDesc, int page, int size, String sortBy);
	 
	 ResultContainer getProductsForCategoryAndPrice(String lcl, String currency, String categoryDesc, Double price, int page, int size, String sortBy);
	 
	 ResultContainer getProductsForCategoryAndBrandAndPrice(String lcl, String currency, String categoryDesc, String brandDesc, Double price, int page, int size, String sortBy);
	 
	 ResultContainer findProduct(String lcl, String currency, String categoryCode, String term, int page, int size, String sortBy, List<CategoryFacet> selectedFacets);
	 
	 List<Product> getPreviewProductsForCategory(String lcl, String currency, Long categoryId);
	 
	 public void recurseCategories(List<Category> pcl, Category pc);
	 
}
