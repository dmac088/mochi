package io.nzbee.resources.tag;

import org.springframework.hateoas.RepresentationModel;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.nzbee.view.product.tag.facet.TagFacetView;

public class TagResource  extends RepresentationModel<TagResource> {

	private final TagFacetView data;
	
	@JsonCreator
	public TagResource(@JsonProperty("tag") TagFacetView tag) {
		this.data = tag;
		
	}
	
	public TagFacetView getData() {
		return data;
	}
	
	
}
