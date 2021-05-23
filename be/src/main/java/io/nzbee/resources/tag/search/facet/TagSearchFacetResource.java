package io.nzbee.resources.tag.search.facet;

import org.springframework.hateoas.RepresentationModel;

import io.nzbee.search.facet.EntityFacet;

public class TagSearchFacetResource extends RepresentationModel<TagSearchFacetResource>  {
	
	private final EntityFacet data;
	
	public TagSearchFacetResource(EntityFacet tag) {
		this.data = tag;
	}

	public EntityFacet getData() {
		return data;
	}
	
}
