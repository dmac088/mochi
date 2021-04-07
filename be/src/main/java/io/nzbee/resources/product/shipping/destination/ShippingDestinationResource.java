package io.nzbee.resources.product.shipping.destination;

import org.springframework.hateoas.RepresentationModel;
import io.nzbee.view.product.shipping.destination.ShippingDestinationView;

public class ShippingDestinationResource  extends RepresentationModel<ShippingDestinationResource> {

	private final ShippingDestinationView data;
	
	public ShippingDestinationResource(final ShippingDestinationView product) {
		this.data = product;
	}
	
	public ShippingDestinationView getData() {
		return data;
	}
	
}
