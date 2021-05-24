package io.nzbee.resources.tag.browse.facet;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.CollectionModel;
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
	
    @Override
    public CollectionModel<TagBrowseFacetResource> toCollectionModel(Iterable<? extends TagFacetView> views) 
    {
        CollectionModel<TagBrowseFacetResource> tags = super.toCollectionModel(views);
         
        tags.add(linkTo(methodOn(TagController.class).getTags(null, null, null, null)).withSelfRel());
         
        return tags;
    }

}
