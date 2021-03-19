package io.nzbee.resources.product.shipping.type;

import org.springframework.hateoas.RepresentationModel;
import io.nzbee.view.product.shipping.type.ShippingTypeDTO;

public class ShippingTypeResource  extends RepresentationModel<ShippingTypeResource> {

	private final ShippingTypeDTO data;
	
	public ShippingTypeResource(final ShippingTypeDTO product) {
		this.data = product;
	}
	
	public ShippingTypeDTO getData() {
		return data;
	}
	
}
