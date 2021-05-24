package io.nzbee.resources.dto;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.RepresentationModel;
import io.nzbee.resources.product.physical.light.PhysicalProductLightModel;

public class BrowseProductResultDto  extends RepresentationModel<PhysicalProductLightModel> {

	private PagedModel<EntityModel<PhysicalProductLightModel>> products;
	
	public BrowseProductResultDto(PagedModel<EntityModel<PhysicalProductLightModel>> products) {
		this.products = products;
	}
	
	public PagedModel<EntityModel<PhysicalProductLightModel>> getProducts() {
		return this.products;
	}
	
}
