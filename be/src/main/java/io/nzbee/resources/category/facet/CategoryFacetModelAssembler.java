package io.nzbee.resources.category.facet;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import io.nzbee.resources.controllers.CategoryController;
import io.nzbee.resources.controllers.ProductController;
import io.nzbee.search.facet.EntityFacetHierarchical;

@Component
public class CategoryFacetModelAssembler extends RepresentationModelAssemblerSupport<EntityFacetHierarchical, CategoryFacetModel> {

	public CategoryFacetModelAssembler() {
		super(CategoryController.class, CategoryFacetModel.class);
	}
	
	@Override
	public CategoryFacetModel toModel(EntityFacetHierarchical category) {
		
		CategoryFacetModel cfm = new CategoryFacetModel(category);
		
		cfm.add(linkTo(methodOn(ProductController.class).getProducts(null, null, null, null, null, null, null))
						.withRel("products"));
		
		return cfm;
	}
    
}