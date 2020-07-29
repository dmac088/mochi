package io.nzbee.resources.tag;

import org.springframework.hateoas.RepresentationModel;

import io.nzbee.search.facet.EntityFacet;

public class TagFacetResource extends RepresentationModel<TagFacetResource>  {
	
	private final EntityFacet data;
	
	public TagFacetResource(EntityFacet tag) {
		this.data = tag;
	}

	public EntityFacet getData() {
		return data;
	}
	
}
