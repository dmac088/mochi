package io.javabrains.springbootstarter.services;

import java.util.List;

import org.hibernate.search.query.facet.Facet;
import org.springframework.data.domain.Page;

public class ResultContainer {
	
	Page<Product> products;
	
	List<Facet> facets;

	public Page<Product> getProducts() {
		return products;
	}

	public void setProducts(Page<Product> products) {
		this.products = products;
	}

	public List<Facet> getFacets() {
		return facets;
	}

	public void setFacets(List<Facet> facets) {
		this.facets = facets;
	}
	
}
