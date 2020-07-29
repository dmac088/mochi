package io.nzbee.resources.category;

import org.springframework.hateoas.RepresentationModel;
import io.nzbee.search.dto.facet.EntityFacet;

public class CategoryFacetResource extends RepresentationModel<CategoryFacetResource>  {
	
	private final EntityFacet data;
	
	public CategoryFacetResource(EntityFacet category) {
		this.data = category;
	}

	public EntityFacet getData() {
		return data;
	}
	
}
