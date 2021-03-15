package io.nzbee.resources.product;

import org.springframework.hateoas.RepresentationModel;

import io.nzbee.dto.product.physical.PhysicalProductDTOFull;

public class ProductFullResource  extends RepresentationModel<ProductFullResource> {

	private final PhysicalProductDTOFull data;
	
	public ProductFullResource(final PhysicalProductDTOFull product) {
		this.data = product;
	}
	
	public PhysicalProductDTOFull getData() {
		return data;
	}
	
}
