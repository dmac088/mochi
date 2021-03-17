package io.nzbee.resources.search;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.RepresentationModel;
import io.nzbee.resources.product.ProductResource;
import java.util.Set;


public class SearchResultResource extends RepresentationModel<SearchResultResource> {
	
    private PagedModel<EntityModel<ProductResource>> products;
    
    private Set<SearchFacetResource> facets;
    
	public SearchResultResource(	PagedModel<EntityModel<ProductResource>> products,
									Set<SearchFacetResource> ssf) {

    	this.products = products;
    	
		this.facets = ssf;
    }

	public PagedModel<EntityModel<ProductResource>> getProducts() {
		return products;
	}

	public Set<SearchFacetResource> getFacets() {
		return facets;
	}
	
}
