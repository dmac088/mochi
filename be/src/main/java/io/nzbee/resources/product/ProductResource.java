package io.nzbee.resources.product;

import org.springframework.hateoas.RepresentationModel;
import io.nzbee.domain.product.Product;

public class ProductResource  extends RepresentationModel<ProductResource> {

	private final Product data;
	
	public ProductResource(final Product product) {
		this.data = product;
	}
	
	public Product getData() {
		return data;
	}
	
}
