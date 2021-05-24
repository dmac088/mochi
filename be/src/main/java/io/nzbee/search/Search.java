package io.nzbee.search;

import java.util.Set;

import org.springframework.hateoas.PagedModel;

import io.nzbee.resources.product.physical.light.PhysicalProductLightModel;
import io.nzbee.search.facet.IFacet;

public class Search {
	
	PagedModel<PhysicalProductLightModel> products;
	
	Set<IFacet> facets;

	public PagedModel<PhysicalProductLightModel> getProducts() {
		return products;
	}

	public void setProducts(PagedModel<PhysicalProductLightModel> products) {
		this.products = products;
	}
	
	public Set<IFacet> getFacets() {
		return facets;
	}

	public void setFacets(Set<IFacet> navFacets) {
		this.facets = navFacets;
	}
}
