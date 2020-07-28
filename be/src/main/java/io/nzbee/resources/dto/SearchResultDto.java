package io.nzbee.resources.dto;

import org.springframework.hateoas.RepresentationModel;
import io.nzbee.resources.product.ProductResource;
import io.nzbee.search.dto.facet.IFacet;
import java.util.Set;
import org.springframework.data.domain.Page;


public class SearchResultDto extends RepresentationModel<SearchResultDto> {
	
    private Page<ProductResource> products;
    
    private Set<IFacet> facets;
    
	public SearchResultDto(	Page<ProductResource> products,
							Set<IFacet> facets) {

    	this.products = products;
    	
		this.facets = facets;
    }

	public Page<ProductResource> getProducts() {
		return products;
	}

	public Set<IFacet> getFacets() {
		return facets;
	}
	
}
