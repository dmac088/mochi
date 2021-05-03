package io.nzbee.resources.tag;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import io.nzbee.resources.controllers.TagController;
import io.nzbee.view.product.tag.facet.TagFacetView;

@Component
public class TagResourceAssembler extends RepresentationModelAssemblerSupport<TagFacetView, TagResource> {

	public TagResourceAssembler() {
		super(TagController.class, TagResource.class);
	}


	@Override
	public TagResource toModel(TagFacetView t) {
		TagResource tr = new TagResource(t);
		return tr;
	}

}
