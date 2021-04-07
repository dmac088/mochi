package io.nzbee.search;

import java.util.Set;

import org.springframework.hateoas.PagedModel;

import io.nzbee.resources.product.physical.light.PhysicalProductLightResource;
import io.nzbee.search.facet.IFacet;

public class Search {
	
	PagedModel<PhysicalProductLightResource> products;
	
	Set<IFacet> facets;

	public PagedModel<PhysicalProductLightResource> getProducts() {
		return products;
	}

	public void setProducts(PagedModel<PhysicalProductLightResource> products) {
		this.products = products;
	}
	
	public Set<IFacet> getFacets() {
		return facets;
	}

	public void setFacets(Set<IFacet> navFacets) {
		this.facets = navFacets;
	}
}
