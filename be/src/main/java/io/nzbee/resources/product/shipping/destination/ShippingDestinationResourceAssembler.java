package io.nzbee.resources.product.shipping.destination;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import io.nzbee.resources.controllers.ProductController;
import io.nzbee.view.product.shipping.destination.ShippingDestinationDTO;

@Component
public class ShippingDestinationResourceAssembler extends RepresentationModelAssemblerSupport<ShippingDestinationDTO, ShippingDestinationResource> {
	
	public ShippingDestinationResourceAssembler() {
		super(ProductController.class, ShippingDestinationResource.class);
	}

	@Override
	public ShippingDestinationResource toModel(ShippingDestinationDTO product) {
		ShippingDestinationResource pr = new ShippingDestinationResource(product);

		return pr;
	}
}