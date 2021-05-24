package io.nzbee.resources.tag.browse.facet;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.nzbee.view.product.tag.facet.TagFacetView;

@Relation(collectionRelation="tags", itemRelation="tag")
public class TagBrowseFacetResource  extends RepresentationModel<TagBrowseFacetResource> {
	private final TagFacetView data;
	
	@JsonCreator
	public TagBrowseFacetResource(@JsonProperty("tag") TagFacetView tag) {
		this.data = tag;
	}
	
	public TagFacetView getData() {
		return data;
	}
	
}
