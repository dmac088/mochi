package io.nzbee.resources.product;

import org.springframework.hateoas.RepresentationModel;

import io.nzbee.search.facet.EntityFacet;

public class PriceFacetResource extends RepresentationModel<PriceFacetResource>  {
	
	private final EntityFacet data;
	
	public PriceFacetResource(EntityFacet price) {
		this.data = price;
	}

	public EntityFacet getData() {
		return data;
	}
	
}
