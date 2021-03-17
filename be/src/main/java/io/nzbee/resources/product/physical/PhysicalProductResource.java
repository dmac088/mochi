package io.nzbee.resources.product.physical;

import org.springframework.hateoas.RepresentationModel;

import io.nzbee.view.product.physical.PhysicalProductDTO;

public class PhysicalProductResource  extends RepresentationModel<PhysicalProductResource> {

	private final PhysicalProductDTO data;
	
	public PhysicalProductResource(final PhysicalProductDTO product) {
		this.data = product;
	}
	
	public PhysicalProductDTO getData() {
		return data;
	}
	
}
