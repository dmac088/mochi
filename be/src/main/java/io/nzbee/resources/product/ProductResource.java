package io.nzbee.resources.product;

import org.springframework.hateoas.RepresentationModel;
import io.nzbee.dto.product.ProductDTOFull;

public class ProductResource  extends RepresentationModel<ProductResource> {

	private final ProductDTOFull data;
	
	public ProductResource(final ProductDTOFull product) {
		this.data = product;
	}
	
	public ProductDTOFull getData() {
		return data;
	}
	
}
