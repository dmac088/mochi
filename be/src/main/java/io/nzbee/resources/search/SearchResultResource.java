package io.nzbee.resources.search;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.RepresentationModel;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.nzbee.resources.product.physical.light.PhysicalProductLightModel;

import java.util.Set;

public class SearchResultResource extends RepresentationModel<SearchResultResource> {
	
	@JsonProperty("searchResults")
    private PagedModel<EntityModel<PhysicalProductLightModel>> products;
    
	@JsonProperty("searchFacets")
    private Set<SearchFacetModel> facets;
    
	public SearchResultResource(	PagedModel<EntityModel<PhysicalProductLightModel>> products,
									Set<SearchFacetModel> ssf) {

    	this.products = products;
    	
		this.facets = ssf;
    }

	public PagedModel<EntityModel<PhysicalProductLightModel>> getProducts() {
		return products;
	}

	public Set<SearchFacetModel> getFacets() {
		return facets;
	}
	
}
