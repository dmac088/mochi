package io.nzbee.resources.brand.searchFacet;

import org.springframework.hateoas.RepresentationModel;

import io.nzbee.search.facet.EntityFacet;

public class BrandSearchFacetResource extends RepresentationModel<BrandSearchFacetResource>  {
	
	private final EntityFacet data;
	
	public BrandSearchFacetResource(EntityFacet brand) {
		this.data = brand;
	}

	public EntityFacet getData() {
		return data;
	}
	
}
