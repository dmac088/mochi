package io.nzbee.resources.product;

import org.springframework.hateoas.RepresentationModel;

import io.nzbee.view.product.physical.PhysicalProductDTOLight;

public class ProductLightResource  extends RepresentationModel<ProductLightResource> {

	private final PhysicalProductDTOLight data;
	
	public ProductLightResource(final PhysicalProductDTOLight product) {
		this.data = product;
	}
	
	public PhysicalProductDTOLight getData() {
		return data;
	}
	
}
