package io.nzbee.ui.component.web.search;

import org.springframework.data.domain.Page;

import io.nzbee.domain.product.Product;
import io.nzbee.ui.component.web.search.facet.SearchFacetContainer;

public class Search {
	
	Page<Product> products;
	
	SearchFacetContainer navFacets;

	public Page<Product> getProducts() {
		return products;
	}

	public void setProducts(Page<Product> products) {
		this.products = products;
	}
	
	public SearchFacetContainer getFacets() {
		return navFacets;
	}

	public void setFacets(SearchFacetContainer navFacets) {
		this.navFacets = navFacets;
	}
}
