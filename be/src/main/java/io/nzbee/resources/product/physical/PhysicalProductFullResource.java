package io.nzbee.resources.product.physical;

import org.springframework.hateoas.RepresentationModel;

import io.nzbee.view.product.physical.PhysicalProductDTOFull;

public class PhysicalProductFullResource  extends RepresentationModel<PhysicalProductFullResource> {

	private final PhysicalProductDTOFull data;
	
	public PhysicalProductFullResource(final PhysicalProductDTOFull product) {
		this.data = product;
	}
	
	public PhysicalProductDTOFull getData() {
		return data;
	}
	
}
