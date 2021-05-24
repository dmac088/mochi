package io.nzbee.resources.tag.search.facet;
import org.springframework.hateoas.server.core.Relation;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import io.nzbee.resources.controllers.CategoryController;
import io.nzbee.search.facet.EntityFacet;

@Component
@Relation(collectionRelation="tags", itemRelation="tag")
public class TagSearchFacetModelAssembler extends RepresentationModelAssemblerSupport<EntityFacet, TagSearchFacetModel> {

	public TagSearchFacetModelAssembler() {
		super(CategoryController.class, TagSearchFacetModel.class);
	}
	
	@Override
	public TagSearchFacetModel toModel(EntityFacet tag) {
		return new TagSearchFacetModel(tag);
	}
    
}