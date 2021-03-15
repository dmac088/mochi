package io.nzbee.resources.dto;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.RepresentationModel;

import io.nzbee.resources.product.ProductLightResource;

public class BrowsePhysicalProductResultDto  extends RepresentationModel<ProductLightResource> {

	private PagedModel<EntityModel<ProductLightResource>> products;
	
	public BrowsePhysicalProductResultDto(PagedModel<EntityModel<ProductLightResource>> products) {
		this.products = products;
	}
	
	public PagedModel<EntityModel<ProductLightResource>> getProducts() {
		return this.products;
	}
	
}
