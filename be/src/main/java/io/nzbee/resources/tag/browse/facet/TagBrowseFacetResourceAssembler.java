package io.nzbee.resources.tag.browse.facet;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import io.nzbee.resources.controllers.TagController;
import io.nzbee.view.product.tag.facet.TagFacetView;

@Component
public class TagBrowseFacetResourceAssembler extends RepresentationModelAssemblerSupport<TagFacetView, TagBrowseFacetResource> {

	public TagBrowseFacetResourceAssembler() {
		super(TagController.class, TagBrowseFacetResource.class);
	}


	@Override
	public TagBrowseFacetResource toModel(TagFacetView t) {
		TagBrowseFacetResource tr = new TagBrowseFacetResource(t);
		return tr;
	}

}
