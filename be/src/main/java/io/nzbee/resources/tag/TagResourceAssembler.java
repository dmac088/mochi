package io.nzbee.resources.tag;

//import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
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
		TagResource tr = new TagResource();
//		tr.add(linkTo(methodOn(TagController.class).get(	t.getLocale(),
//															t.getCurrency(),
//															t.getTagCode())).withSelfRel());
		return tr;
	}

}
