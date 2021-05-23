package io.nzbee.resources.category.search.facet;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import io.nzbee.resources.controllers.CategoryController;
import io.nzbee.search.facet.EntityFacet;

@Component
public class CategorySearchFacetResourceAssembler extends RepresentationModelAssemblerSupport<EntityFacet, CategorySearchFacetResource> {

	public CategorySearchFacetResourceAssembler() {
		super(CategoryController.class, CategorySearchFacetResource.class);
	}
	
	@Override
	public CategorySearchFacetResource toModel(EntityFacet category) {
		return new CategorySearchFacetResource(category);
	}
    
}