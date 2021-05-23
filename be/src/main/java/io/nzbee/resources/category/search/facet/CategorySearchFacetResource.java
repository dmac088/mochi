package io.nzbee.resources.category.search.facet;

import org.springframework.hateoas.RepresentationModel;

import io.nzbee.search.facet.EntityFacet;

public class CategorySearchFacetResource extends RepresentationModel<CategorySearchFacetResource>  {
	
	private final EntityFacet data;
	
	public CategorySearchFacetResource(EntityFacet category) {
		this.data = category;
	}

	public EntityFacet getData() {
		return data;
	}
	
}
