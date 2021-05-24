package io.nzbee.resources.tag.facet;
import org.springframework.hateoas.server.core.Relation;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import io.nzbee.resources.controllers.TagController;
import io.nzbee.search.facet.EntityFacet;

@Component
@Relation(collectionRelation="tags", itemRelation="tag")
public class TagFacetModelAssembler extends RepresentationModelAssemblerSupport<EntityFacet, TagFacetModel> {

	public TagFacetModelAssembler() {
		super(TagController.class, TagFacetModel.class);
	}
	
	@Override
	public TagFacetModel toModel(EntityFacet tag) {
		return new TagFacetModel(tag);
	}
    
}