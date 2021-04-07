package io.nzbee.resources.product.shipping.type;

import org.springframework.hateoas.RepresentationModel;
import io.nzbee.view.product.shipping.type.ShippingTypeView;

public class ShippingTypeResource  extends RepresentationModel<ShippingTypeResource> {

	private final ShippingTypeView data;
	
	public ShippingTypeResource(final ShippingTypeView product) {
		this.data = product;
	}
	
	public ShippingTypeView getData() {
		return data;
	}
	
}
