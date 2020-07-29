package io.nzbee.resources.tag;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import io.nzbee.domain.tag.Tag;
import io.nzbee.resources.controllers.TagController;

@Component
public class TagResourceAssembler extends RepresentationModelAssemblerSupport<Tag, TagResource> {

	public TagResourceAssembler() {
		super(TagController.class, TagResource.class);
	}


	@Override
	public TagResource toModel(Tag t) {
		TagResource tr = new TagResource(t);
		return tr;
	}

}
