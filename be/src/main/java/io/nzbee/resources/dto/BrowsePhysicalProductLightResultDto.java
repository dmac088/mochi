package io.nzbee.resources.dto;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.RepresentationModel;

import io.nzbee.resources.product.physical.light.PhysicalProductLightResource;

public class BrowsePhysicalProductLightResultDto  extends RepresentationModel<PhysicalProductLightResource> {

	private PagedModel<EntityModel<PhysicalProductLightResource>> products;
	
	public BrowsePhysicalProductLightResultDto(PagedModel<EntityModel<PhysicalProductLightResource>> products) {
		this.products = products;
	}
	
	public PagedModel<EntityModel<PhysicalProductLightResource>> getProducts() {
		return this.products;
	}
	
}
