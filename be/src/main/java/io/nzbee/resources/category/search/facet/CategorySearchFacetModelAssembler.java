package io.nzbee.resources.category.search.facet;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import io.nzbee.resources.controllers.CategoryController;
import io.nzbee.search.facet.EntityFacet;

@Component
public class CategorySearchFacetModelAssembler extends RepresentationModelAssemblerSupport<EntityFacet, CategorySearchFacetModel> {

	public CategorySearchFacetModelAssembler() {
		super(CategoryController.class, CategorySearchFacetModel.class);
	}
	
	@Override
	public CategorySearchFacetModel toModel(EntityFacet category) {
		return new CategorySearchFacetModel(category);
	}
    
}