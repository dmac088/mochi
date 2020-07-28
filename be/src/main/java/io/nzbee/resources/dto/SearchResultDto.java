package io.nzbee.resources.dto;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.RepresentationModel;
import io.nzbee.resources.product.ProductResource;
import io.nzbee.search.dto.facet.IFacet;
import java.util.Set;


public class SearchResultDto extends RepresentationModel<SearchResultDto> {
	
    private PagedModel<EntityModel<ProductResource>> products;
    
    private Set<IFacet> facets;
    
	public SearchResultDto(	PagedModel<EntityModel<ProductResource>> products,
							Set<IFacet> facets) {

    	this.products = products;
    	
		this.facets = facets;
    }

	public PagedModel<EntityModel<ProductResource>> getProducts() {
		return products;
	}

	public Set<IFacet> getFacets() {
		return facets;
	}
	
}
