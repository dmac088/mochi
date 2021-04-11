package io.nzbee.resources.product.physical.full;

import org.springframework.hateoas.RepresentationModel;

import io.nzbee.view.product.physical.full.PhysicalProductFullView;

public class PhysicalProductFullResource  extends RepresentationModel<PhysicalProductFullResource> {

	private final PhysicalProductFullView data;
	
	public PhysicalProductFullResource(final PhysicalProductFullView product) {
		this.data = product;
	}
	
	public PhysicalProductFullView getData() {
		return data;
	}
	
}
