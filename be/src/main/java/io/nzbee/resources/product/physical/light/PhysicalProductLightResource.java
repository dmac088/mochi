package io.nzbee.resources.product.physical.light;

import org.springframework.hateoas.RepresentationModel;

import io.nzbee.view.product.physical.PhysicalProductLightView;

public class PhysicalProductLightResource  extends RepresentationModel<PhysicalProductLightResource> {

	private final PhysicalProductLightView data;
	
	public PhysicalProductLightResource(final PhysicalProductLightView product) {
		this.data = product;
	}
	
	public PhysicalProductLightView getData() {
		return data;
	}
	
}
