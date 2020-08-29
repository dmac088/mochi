package io.nzbee.resources.product;

import org.springframework.hateoas.RepresentationModel;
import io.nzbee.dto.product.ProductDTO;

public class ProductResource  extends RepresentationModel<ProductResource> {

	private final ProductDTO data;
	
	public ProductResource(final ProductDTO product) {
		this.data = product;
	}
	
	public ProductDTO getData() {
		return data;
	}
	
}
