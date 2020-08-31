package io.nzbee.resources.dto;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.RepresentationModel;

import io.nzbee.resources.product.ProductLightResource;

public class BrowseResultDto  extends RepresentationModel<ProductLightResource> {

	private PagedModel<EntityModel<ProductLightResource>> products;
	
	public BrowseResultDto(PagedModel<EntityModel<ProductLightResource>> products) {
		this.products = products;
	}
	
	public PagedModel<EntityModel<ProductLightResource>> getProducts() {
		return this.products;
	}
	
}
