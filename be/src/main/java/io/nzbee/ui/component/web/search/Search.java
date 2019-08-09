package io.nzbee.ui.component.web.search;

import org.springframework.data.domain.Page;
import io.nzbee.domain.Product;
import io.nzbee.ui.component.web.facet.NavFacetContainer;

public class Search {
	
	Page<Product> products;
	
	NavFacetContainer navFacets;

	public Page<Product> getProducts() {
		return products;
	}

	public void setProducts(Page<Product> products) {
		this.products = products;
	}
	
	public NavFacetContainer getFacets() {
		return navFacets;
	}

	public void setFacets(NavFacetContainer navFacets) {
		this.navFacets = navFacets;
	}
}
