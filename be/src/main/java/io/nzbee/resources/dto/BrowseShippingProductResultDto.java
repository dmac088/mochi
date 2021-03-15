package io.nzbee.resources.dto;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.RepresentationModel;
import io.nzbee.resources.product.shipping.ShippingProductResource;

public class BrowseShippingProductResultDto  extends RepresentationModel<ShippingProductResource> {

	private PagedModel<EntityModel<ShippingProductResource>> products;
	
	public BrowseShippingProductResultDto(PagedModel<EntityModel<ShippingProductResource>> products) {
		this.products = products;
	}
	
	public PagedModel<EntityModel<ShippingProductResource>> getProducts() {
		return this.products;
	}
	
}
