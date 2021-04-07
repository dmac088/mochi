package io.nzbee.resources.search;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.RepresentationModel;
import io.nzbee.resources.product.physical.light.PhysicalProductLightResource;

import java.util.Set;


public class SearchResultResource extends RepresentationModel<SearchResultResource> {
	
    private PagedModel<EntityModel<PhysicalProductLightResource>> products;
    
    private Set<SearchFacetResource> facets;
    
	public SearchResultResource(	PagedModel<EntityModel<PhysicalProductLightResource>> products,
									Set<SearchFacetResource> ssf) {

    	this.products = products;
    	
		this.facets = ssf;
    }

	public PagedModel<EntityModel<PhysicalProductLightResource>> getProducts() {
		return products;
	}

	public Set<SearchFacetResource> getFacets() {
		return facets;
	}
	
}
