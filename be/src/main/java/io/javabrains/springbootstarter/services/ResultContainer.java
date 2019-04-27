package io.javabrains.springbootstarter.services;

import java.util.List;
import org.springframework.data.domain.Page;

import io.javabrains.springbootstarter.domain.Product;
import io.javabrains.springbootstarter.dto.CategorySidebarDTO;

public class ResultContainer {
	
	Page<Product> products;
	
	List<CategorySidebarDTO> categoryFacets;
	
	//List<CategoryFacet> brandFacets;

	public Page<Product> getProducts() {
		return products;
	}

	public void setProducts(Page<Product> products) {
		this.products = products;
	}

	public List<CategorySidebarDTO> getCategoryFacets() {
		return categoryFacets;
	}

	public void setCategoryFacets(List<CategorySidebarDTO> facets) {
		this.categoryFacets = facets;
	}
	
}
