package io.nzbee.resources.tag;

import org.springframework.hateoas.RepresentationModel;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.nzbee.domain.tag.Tag;

public class TagResource  extends RepresentationModel<TagResource> {

	private final Tag data;
	
	@JsonCreator
	public TagResource(@JsonProperty("tag") Tag tag) {
		this.data = tag;
		
	}
	
	public Tag getData() {
		return data;
	}
	
	
}
