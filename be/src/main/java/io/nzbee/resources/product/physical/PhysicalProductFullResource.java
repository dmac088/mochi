package io.nzbee.resources.product.physical;

import org.springframework.hateoas.RepresentationModel;

import io.nzbee.view.product.physical.PhysicalProductDTO;

public class PhysicalProductFullResource  extends RepresentationModel<PhysicalProductFullResource> {

	private final PhysicalProductDTO data;
	
	public PhysicalProductFullResource(final PhysicalProductDTO product) {
		this.data = product;
	}
	
	public PhysicalProductDTO getData() {
		return data;
	}
	
}
