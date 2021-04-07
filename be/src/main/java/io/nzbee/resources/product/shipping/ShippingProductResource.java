package io.nzbee.resources.product.shipping;

import org.springframework.hateoas.RepresentationModel;
import io.nzbee.view.product.shipping.ShippingProductView;

public class ShippingProductResource  extends RepresentationModel<ShippingProductResource> {

	private final ShippingProductView data;
	
	public ShippingProductResource(final ShippingProductView product) {
		this.data = product;
	}
	
	public ShippingProductView getData() {
		return data;
	}
	
}
