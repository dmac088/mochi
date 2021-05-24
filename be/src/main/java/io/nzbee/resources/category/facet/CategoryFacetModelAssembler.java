package io.nzbee.resources.category.facet;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import io.nzbee.resources.controllers.CategoryController;
import io.nzbee.search.facet.EntityFacetHierarchical;

@Component
public class CategoryFacetModelAssembler extends RepresentationModelAssemblerSupport<EntityFacetHierarchical, CategoryFacetModel> {

	public CategoryFacetModelAssembler() {
		super(CategoryController.class, CategoryFacetModel.class);
	}
	
	@Override
	public CategoryFacetModel toModel(EntityFacetHierarchical category) {
		return new CategoryFacetModel(category);
	}
    
}