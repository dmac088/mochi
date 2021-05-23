package io.nzbee.resources.tag.browse.facet;

import org.springframework.hateoas.RepresentationModel;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import io.nzbee.view.product.tag.facet.TagFacetView;

@JsonRootName("tagFacets")
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
