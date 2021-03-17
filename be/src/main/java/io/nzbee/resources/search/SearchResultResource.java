package io.nzbee.resources.search;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.RepresentationModel;

import io.nzbee.resources.product.physical.ProductLightResource;

import java.util.Set;


public class SearchResultResource extends RepresentationModel<SearchResultResource> {
	
    private PagedModel<EntityModel<ProductLightResource>> products;
    
    private Set<SearchFacetResource> facets;
    
	public SearchResultResource(	PagedModel<EntityModel<ProductLightResource>> products,
									Set<SearchFacetResource> ssf) {

    	this.products = products;
    	
		this.facets = ssf;
    }

	public PagedModel<EntityModel<ProductLightResource>> getProducts() {
		return products;
	}

	public Set<SearchFacetResource> getFacets() {
		return facets;
	}
	
}
