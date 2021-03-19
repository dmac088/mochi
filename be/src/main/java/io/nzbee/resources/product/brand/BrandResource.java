package io.nzbee.resources.product.brand;

import org.springframework.hateoas.RepresentationModel;
import io.nzbee.view.product.shipping.ShippingProductDTO;

public class BrandResource  extends RepresentationModel<BrandResource> {

	private final ShippingProductDTO data;
	
	public BrandResource(final ShippingProductDTO product) {
		this.data = product;
	}
	
	public ShippingProductDTO getData() {
		return data;
	}
	
}
