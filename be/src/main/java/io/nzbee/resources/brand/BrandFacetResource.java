package io.nzbee.resources.brand;

import org.springframework.hateoas.RepresentationModel;

import io.nzbee.search.facet.EntityFacet;

public class BrandFacetResource extends RepresentationModel<BrandFacetResource>  {
	
	private final EntityFacet data;
	
	public BrandFacetResource(EntityFacet brand) {
		this.data = brand;
	}

	public EntityFacet getData() {
		return data;
	}
	
}
