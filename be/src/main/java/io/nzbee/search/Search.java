package io.nzbee.search;

import java.util.Set;

import org.springframework.hateoas.PagedModel;
import io.nzbee.resources.product.ProductResource;
import io.nzbee.search.dto.facet.IFacet;

public class Search {
	
	PagedModel<ProductResource> products;
	
	Set<IFacet> facets;

	public PagedModel<ProductResource> getProducts() {
		return products;
	}

	public void setProducts(PagedModel<ProductResource> products) {
		this.products = products;
	}
	
	public Set<IFacet> getFacets() {
		return facets;
	}

	public void setFacets(Set<IFacet> navFacets) {
		this.facets = navFacets;
	}
}
