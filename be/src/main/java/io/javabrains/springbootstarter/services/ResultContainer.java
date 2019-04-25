package io.javabrains.springbootstarter.services;

import java.util.List;
import org.springframework.data.domain.Page;

import io.javabrains.springbootstarter.facets.CategoryFacet;

public class ResultContainer {
	
	Page<Product> products;
	
	List<CategoryFacet> categories;
	
	//List<CategoryFacet> brandFacets;

	public Page<Product> getProducts() {
		return products;
	}

	public void setProducts(Page<Product> products) {
		this.products = products;
	}

	public List<CategoryFacet> getCategories() {
		return categories;
	}

	public void setCategories(List<CategoryFacet> categories) {
		this.categories = categories;
	}
	
}
