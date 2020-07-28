package io.nzbee.resources.dto;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.RepresentationModel;

import io.nzbee.resources.product.ProductResource;

public class BrowseResultDto  extends RepresentationModel<ProductResource> {

	private PagedModel<EntityModel<ProductResource>> products;
	
	public BrowseResultDto(PagedModel<EntityModel<ProductResource>> products) {
		this.products = products;
	}
	
	public PagedModel<EntityModel<ProductResource>> getProducts() {
		return this.products;
	}
	
}
