package io.nzbee.search;

import java.util.Set;
import org.springframework.hateoas.PagedResources;
import io.nzbee.resources.product.ProductResource;
import io.nzbee.search.dto.facet.IFacet;

public class Search {
	
	PagedResources<ProductResource> products;
	
	Set<IFacet> facets;

	public PagedResources<ProductResource> getProducts() {
		return products;
	}

	public void setProducts(PagedResources<ProductResource> products) {
		this.products = products;
	}
	
	public Set<IFacet> getFacets() {
		return facets;
	}

	public void setFacets(Set<IFacet> navFacets) {
		this.facets = navFacets;
	}
}
