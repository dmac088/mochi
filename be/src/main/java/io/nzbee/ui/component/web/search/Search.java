package io.nzbee.ui.component.web.search;

import java.util.List;
import org.springframework.data.domain.Page;

import io.nzbee.domain.Product;
import io.nzbee.ui.component.web.facet.NavFacet;

public class Search {
	
	Page<Product> products;
	
	List<NavFacet> Facets;

	public Page<Product> getProducts() {
		return products;
	}

	public void setProducts(Page<Product> products) {
		this.products = products;
	}

	public List<NavFacet> getFacets() {
		return Facets;
	}

	public void setFacets(List<NavFacet> facets) {
		this.Facets = facets;
	}
	
}
