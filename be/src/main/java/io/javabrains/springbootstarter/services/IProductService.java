package io.javabrains.springbootstarter.services;


import java.util.List;
import java.util.stream.Collectors;

import io.javabrains.springbootstarter.domain.Product;
import io.javabrains.springbootstarter.dto.ProductsDTO;
import io.javabrains.springbootstarter.dto.SidebarFacetDTO;
import io.javabrains.springbootstarter.entity.Category;

public interface IProductService {
	
	 ProductsDTO getProducts(String lcl, String currency, int page, int size, String sortBy);
	 
	 public Product getProduct(String lcl, String currency, Long id);
	 
	 ProductsDTO getProductsForCategory(String lcl, String currency, String categoryDesc, int page, int size, String sortBy);
	 
	 ProductsDTO getProductsForCategory(String lcl, String currency, String categoryDesc, Double price, int page, int size, String sortBy);
	 
	 ProductsDTO getProductsForCategoryAndBrand(String lcl, String currency, String categoryDesc, String brandDesc, int page, int size, String sortBy);
	 
	 ProductsDTO getProductsForCategoryAndPrice(String lcl, String currency, String categoryDesc, Double price, int page, int size, String sortBy);
	 
	 ProductsDTO getProductsForCategoryAndBrandAndPrice(String lcl, String currency, String categoryDesc, Double price, int page, int size, String sortBy, List<SidebarFacetDTO> selectedFacets);
	 
	 ProductsDTO findProduct(String lcl, String currency, String categoryCode, String term, int page, int size, String sortBy, List<SidebarFacetDTO> selectedFacets);
	 
	 List<Product> getPreviewProductsForCategory(String lcl, String currency, Long categoryId);
	 
	 public static List<Category> recurseCategories(List<Category> pcl, Category pc) {
		if(pc == null) { return pcl; }
	    pcl.add(pc); 
	    if(pc.getChildren().size() == 0) { return pcl; }
	    return pc.getChildren().stream().map(child -> recurseCategories(pcl, child)).collect(Collectors.toList()).get(0);
	 }
	 
}
