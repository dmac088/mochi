package io.nzbee.resources.product;

import org.springframework.hateoas.RepresentationModel;
import io.nzbee.dto.product.ProductDTOLight;

public class ProductLightResource  extends RepresentationModel<ProductLightResource> {

	private final ProductDTOLight data;
	
	public ProductLightResource(final ProductDTOLight product) {
		this.data = product;
	}
	
	public ProductDTOLight getData() {
		return data;
	}
	
}
