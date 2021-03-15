package io.nzbee.resources.product.shipping;

import org.springframework.hateoas.RepresentationModel;
import io.nzbee.view.product.shipping.ShippingProductDTO;

public class ShippingProductResource  extends RepresentationModel<ShippingProductResource> {

	private final ShippingProductDTO data;
	
	public ShippingProductResource(final ShippingProductDTO product) {
		this.data = product;
	}
	
	public ShippingProductDTO getData() {
		return data;
	}
	
}
