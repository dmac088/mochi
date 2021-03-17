package io.nzbee.resources.dto;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.RepresentationModel;

import io.nzbee.resources.product.ProductResource;
import io.nzbee.resources.product.physical.ProductLightResource;

public class BrowseProductResultDto  extends RepresentationModel<ProductLightResource> {

	private PagedModel<EntityModel<ProductResource>> products;
	
	public BrowseProductResultDto(PagedModel<EntityModel<ProductResource>> products) {
		this.products = products;
	}
	
	public PagedModel<EntityModel<ProductResource>> getProducts() {
		return this.products;
	}
	
}
