package io.nzbee.resources.dto;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.nzbee.resources.product.physical.light.PhysicalProductLightModel;

public class BrowsePhysicalProductLightResultDto  extends RepresentationModel<PhysicalProductLightModel> {

	@JsonProperty("searchResults")
	private PagedModel<EntityModel<PhysicalProductLightModel>> products;
	
	public BrowsePhysicalProductLightResultDto(PagedModel<EntityModel<PhysicalProductLightModel>> products) {
		this.products = products;
	}
	
	public PagedModel<EntityModel<PhysicalProductLightModel>> getProducts() {
		return this.products;
	}
	
}
