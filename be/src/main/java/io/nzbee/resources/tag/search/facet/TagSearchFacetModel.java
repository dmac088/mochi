package io.nzbee.resources.tag.search.facet;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;
import io.nzbee.search.facet.EntityFacet;

@Relation(collectionRelation="tags", itemRelation="tag")
public class TagSearchFacetModel extends RepresentationModel<TagSearchFacetModel>  {
	
	private final EntityFacet data;
	
	public TagSearchFacetModel(EntityFacet tag) {
		this.data = tag;
	}

	public EntityFacet getData() {
		return data;
	}
	
}
