package io.nzbee.resources.tag.search.facet;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import io.nzbee.resources.controllers.CategoryController;
import io.nzbee.search.facet.EntityFacet;

@Component
public class TagSearchFacetResourceAssembler extends RepresentationModelAssemblerSupport<EntityFacet, TagSearchFacetResource> {

	public TagSearchFacetResourceAssembler() {
		super(CategoryController.class, TagSearchFacetResource.class);
	}
	
	@Override
	public TagSearchFacetResource toModel(EntityFacet tag) {
		return new TagSearchFacetResource(tag);
	}
    
}