package io.javabrains.springbootstarter.services;

import java.util.List;

import io.javabrains.springbootstarter.services.CategoryFacet;
import org.springframework.data.domain.Page;

public class ResultContainer {
	
	Page<Product> products;
	
	List<CategoryFacet> categoryFacets;
	
	List<CategoryFacet> brandFacets;

	public Page<Product> getProducts() {
		return products;
	}

	public void setProducts(Page<Product> products) {
		this.products = products;
	}

	public List<CategoryFacet> getCategoryFacets() {
		return categoryFacets;
	}

	public void setCategoryFacets(List<CategoryFacet> categoryFacets) {
		this.categoryFacets = categoryFacets;
	}

	public List<CategoryFacet> getBrandFacets() {
		return brandFacets;
	}

	public void setBrandFacets(List<CategoryFacet> brandFacets) {
		this.brandFacets = brandFacets;
	}
	
}
