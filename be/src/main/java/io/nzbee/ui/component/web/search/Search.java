package io.nzbee.ui.component.web.search;

import org.springframework.hateoas.PagedResources;
import io.nzbee.resources.product.ProductResource;
import io.nzbee.ui.component.web.facet.FacetContainer;

public class Search {
	
	PagedResources<ProductResource> products;
	
	FacetContainer navFacets;

	public PagedResources<ProductResource> getProducts() {
		return products;
	}

	public void setProducts(PagedResources<ProductResource> products) {
		this.products = products;
	}
	
	public FacetContainer getFacets() {
		return navFacets;
	}

	public void setFacets(FacetContainer navFacets) {
		this.navFacets = navFacets;
	}
}
