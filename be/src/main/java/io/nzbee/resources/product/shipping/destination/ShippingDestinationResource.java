package io.nzbee.resources.product.shipping.destination;

import org.springframework.hateoas.RepresentationModel;
import io.nzbee.view.product.shipping.destination.ShippingDestinationDTO;

public class ShippingDestinationResource  extends RepresentationModel<ShippingDestinationResource> {

	private final ShippingDestinationDTO data;
	
	public ShippingDestinationResource(final ShippingDestinationDTO product) {
		this.data = product;
	}
	
	public ShippingDestinationDTO getData() {
		return data;
	}
	
}
