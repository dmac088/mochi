package io.nzbee.resources.category;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import io.nzbee.resources.controllers.CategoryController;
import io.nzbee.search.facet.EntityFacet;

@Component
public class CategoryFacetResourceAssembler extends RepresentationModelAssemblerSupport<EntityFacet, CategoryFacetResource> {

	public CategoryFacetResourceAssembler() {
		super(CategoryController.class, CategoryFacetResource.class);
	}
	
	@Override
	public CategoryFacetResource toModel(EntityFacet category) {
		return new CategoryFacetResource(category);
	}
    
}