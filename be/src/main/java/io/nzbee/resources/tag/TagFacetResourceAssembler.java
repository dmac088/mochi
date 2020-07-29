package io.nzbee.resources.tag;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import io.nzbee.resources.controllers.CategoryController;
import io.nzbee.search.facet.EntityFacet;

@Component
public class TagFacetResourceAssembler extends RepresentationModelAssemblerSupport<EntityFacet, TagFacetResource> {

	public TagFacetResourceAssembler() {
		super(CategoryController.class, TagFacetResource.class);
	}
	
	@Override
	public TagFacetResource toModel(EntityFacet tag) {
		return new TagFacetResource(tag);
	}
    
}