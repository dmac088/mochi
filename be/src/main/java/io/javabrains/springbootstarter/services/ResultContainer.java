package io.javabrains.springbootstarter.services;

import java.util.List;

import org.hibernate.search.query.facet.Facet;
import org.springframework.data.domain.Page;

public class ResultContainer {
	
	Page<Product> products;
	
	List<Facet> categoryFacets;
	
	List<Facet> brandFacets;

	public Page<Product> getProducts() {
		return products;
	}

	public void setProducts(Page<Product> products) {
		this.products = products;
	}

	public List<Facet> getCategoryFacets() {
		return categoryFacets;
	}

	public void setCategoryFacets(List<Facet> categoryFacets) {
		this.categoryFacets = categoryFacets;
	}

	public List<Facet> getBrandFacets() {
		return brandFacets;
	}

	public void setBrandFacets(List<Facet> brandFacets) {
		this.brandFacets = brandFacets;
	}


	
}
