package io.nzbee.resources.product;

import org.springframework.hateoas.RepresentationModel;
import io.nzbee.domain.product.Product;

public class ProductResource  extends RepresentationModel<ProductResource> {

	private final Product product;
	
	public ProductResource(final Product product) {
		this.product = product;
	}
	
	public Product getProduct() {
		return product;
	}
	
}
