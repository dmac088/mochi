package io.nzbee.services;

import java.util.List;

import io.nzbee.domain.Product;
import io.nzbee.dto.SearchDTO;
import io.nzbee.dto.SidebarFacetDTO;
import io.nzbee.entity.Category;

public interface IProductService {
	
	 SearchDTO getProducts(String lcl, String currency, int page, int size, String sortBy);
	 
	 public Product getProduct(String lcl, String currency, Long id);
	 
	 SearchDTO getProductsForCategory(String lcl, String currency, String categoryDesc, int page, int size, String sortBy);
	 
	 SearchDTO getProductsForCategory(String lcl, String currency, String categoryDesc, Double price, int page, int size, String sortBy);
	 
	 SearchDTO getProductsForCategoryAndBrand(String lcl, String currency, String categoryDesc, String brandDesc, int page, int size, String sortBy);
	 
	 SearchDTO getProductsForCategoryAndPrice(String lcl, String currency, String categoryDesc, Double price, int page, int size, String sortBy);
	 
	 SearchDTO getProductsForCategoryAndBrandAndPrice(String lcl, String currency, String categoryDesc, Double price, int page, int size, String sortBy, List<SidebarFacetDTO> selectedFacets);
	 
	 SearchDTO findProduct(String lcl, String currency, String categoryCode, String term, int page, int size, String sortBy, List<SidebarFacetDTO> selectedFacets);
	 
	 SearchDTO getProductTags(String lcl, String curr, String category, List<SidebarFacetDTO> selectedFacets);
	 
	 List<Product> getProducts(String lcl, String currency, Long[] productIds);
	 
	 List<Product> getPreviewProductsForCategory(String lcl, String currency, Long categoryId);
	
	 Double getMaxPrice(String lcl, String curr, String category, List<SidebarFacetDTO> selectedFacets);
	 
	 public static List<Category> recurseCategories(List<Category> list, Category category) {
		if(category == null) { return list; }
		list.add(category);
		if(category.getChildren().isEmpty()) { return list; }
		category.getChildren().stream().forEach(c -> {
			list.add(c);
			recurseCategories(list, c); 
		});
		return list; 
	}
	
}
