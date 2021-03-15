package io.nzbee.resources.bag;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.nzbee.view.bag.BagDTO;

public class BagResource extends RepresentationModel<BagResource> {

	private final BagDTO data;
	
	@JsonCreator
	public BagResource(@JsonProperty("bag") BagDTO bag) {
		this.data = bag;
		
	}
	
	public BagDTO getData() {
		return data;
	}
}
