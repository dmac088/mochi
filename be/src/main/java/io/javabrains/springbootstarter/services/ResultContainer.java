package io.javabrains.springbootstarter.services;

import java.util.List;

import io.javabrains.springbootstarter.services.CategoryFacet;
import org.springframework.data.domain.Page;

public class ResultContainer {
	
	Page<Product> products;
	
	List<Category> categories;
	
	//List<CategoryFacet> brandFacets;

	public Page<Product> getProducts() {
		return products;
	}

	public void setProducts(Page<Product> products) {
		this.products = products;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	
}
