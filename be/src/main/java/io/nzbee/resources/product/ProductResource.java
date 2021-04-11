package io.nzbee.resources.product;

import org.springframework.hateoas.RepresentationModel;

import io.nzbee.view.product.ProductView;

public class ProductResource  extends RepresentationModel<ProductResource> {

	private final ProductView data;
	
	public ProductResource(final ProductView product) {
		this.data = product;
	}
	
	public ProductView getData() {
		return data;
	}
	
}
