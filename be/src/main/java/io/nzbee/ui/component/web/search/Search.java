package io.nzbee.ui.component.web.search;

import org.springframework.data.domain.Page;

import io.nzbee.domain.product.Product;
import io.nzbee.ui.component.web.facet.FacetContainer;

public class Search {
	
	Page<Product> products;
	
	FacetContainer navFacets;

	public Page<Product> getProducts() {
		return products;
	}

	public void setProducts(Page<Product> products) {
		this.products = products;
	}
	
	public FacetContainer getFacets() {
		return navFacets;
	}

	public void setFacets(FacetContainer navFacets) {
		this.navFacets = navFacets;
	}
}
