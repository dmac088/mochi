package io.nzbee.resources.tag.browse.facet;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import io.nzbee.resources.controllers.TagController;
import io.nzbee.view.product.tag.facet.TagFacetView;

@Component
public class TagBrowseFacetModelAssembler extends RepresentationModelAssemblerSupport<TagFacetView, TagBrowseFacetModel> {

	public TagBrowseFacetModelAssembler() {
		super(TagController.class, TagBrowseFacetModel.class);
	}

	@Override
	public TagBrowseFacetModel toModel(TagFacetView t) {
		TagBrowseFacetModel tr = new TagBrowseFacetModel(t);
		return tr;
	}
	
    @Override
    public CollectionModel<TagBrowseFacetModel> toCollectionModel(Iterable<? extends TagFacetView> views) 
    {
        CollectionModel<TagBrowseFacetModel> tags = super.toCollectionModel(views);
         
        tags.add(linkTo(methodOn(TagController.class).getTags(null, null, null, null)).withSelfRel());
         
        return tags;
    }

}
