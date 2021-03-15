package io.nzbee.resources.bag.item;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.nzbee.view.bag.item.BagItemDTOOut;

public class BagItemResource extends RepresentationModel<BagItemResource> {

	private final BagItemDTOOut data;
	
	@JsonCreator
	public BagItemResource(@JsonProperty("bag") BagItemDTOOut bag) {
		this.data = bag;
		
	}
	
	public BagItemDTOOut getData() {
		return data;
	}
}
