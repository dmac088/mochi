package io.nzbee.resources.category.facet;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import io.nzbee.resources.controllers.CategoryController;
import io.nzbee.search.facet.EntityFacet;

@Component
public class CategoryFacetModelAssembler extends RepresentationModelAssemblerSupport<EntityFacet, CategoryFacetModel> {

	public CategoryFacetModelAssembler() {
		super(CategoryController.class, CategoryFacetModel.class);
	}
	
	@Override
	public CategoryFacetModel toModel(EntityFacet category) {
		return new CategoryFacetModel(category);
	}
    
}