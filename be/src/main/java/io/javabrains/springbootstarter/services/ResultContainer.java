package io.javabrains.springbootstarter.services;

import java.util.List;
import org.springframework.data.domain.Page;

import io.javabrains.springbootstarter.domain.Product;
import io.javabrains.springbootstarter.dto.CategorySidebar;

public class ResultContainer {
	
	Page<Product> products;
	
	List<CategorySidebar> categoryFacets;
	
	//List<CategoryFacet> brandFacets;

	public Page<Product> getProducts() {
		return products;
	}

	public void setProducts(Page<Product> products) {
		this.products = products;
	}

	public List<CategorySidebar> getCategoryFacets() {
		return categoryFacets;
	}

	public void setCategoryFacets(List<CategorySidebar> facets) {
		this.categoryFacets = facets;
	}
	
}
