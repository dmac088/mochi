package io.nzbee.search;

import java.util.Set;

import org.springframework.hateoas.PagedModel;

import io.nzbee.resources.product.physical.ProductLightResource;
import io.nzbee.search.facet.IFacet;

public class Search {
	
	PagedModel<ProductLightResource> products;
	
	Set<IFacet> facets;

	public PagedModel<ProductLightResource> getProducts() {
		return products;
	}

	public void setProducts(PagedModel<ProductLightResource> products) {
		this.products = products;
	}
	
	public Set<IFacet> getFacets() {
		return facets;
	}

	public void setFacets(Set<IFacet> navFacets) {
		this.facets = navFacets;
	}
}
