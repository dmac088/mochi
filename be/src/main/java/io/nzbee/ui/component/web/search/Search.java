package io.nzbee.ui.component.web.search;

import java.util.List;
import org.springframework.data.domain.Page;

import io.nzbee.domain.Product;
import io.nzbee.ui.component.web.sidebar.Sidebar;

public class Search {
	
	Page<Product> products;
	
	List<Sidebar> Facets;

	public Page<Product> getProducts() {
		return products;
	}

	public void setProducts(Page<Product> products) {
		this.products = products;
	}

	public List<Sidebar> getFacets() {
		return Facets;
	}

	public void setFacets(List<Sidebar> facets) {
		this.Facets = facets;
	}
	
}
