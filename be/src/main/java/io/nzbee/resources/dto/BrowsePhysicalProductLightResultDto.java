package io.nzbee.resources.dto;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.RepresentationModel;

import io.nzbee.resources.product.physical.ProductLightResource;

public class BrowsePhysicalProductLightResultDto  extends RepresentationModel<ProductLightResource> {

	private PagedModel<EntityModel<ProductLightResource>> products;
	
	public BrowsePhysicalProductLightResultDto(PagedModel<EntityModel<ProductLightResource>> products) {
		this.products = products;
	}
	
	public PagedModel<EntityModel<ProductLightResource>> getProducts() {
		return this.products;
	}
	
}
