package io.nzbee.resources.product.shipping.destination;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
		ShippingDestinationResource sdr = new ShippingDestinationResource(product);
		
		sdr.add(linkTo(methodOn(ProductController.class).getShippingDestination(null, null,
				product.getProductDestinationCode())).withSelfRel());
		
		sdr.add(linkTo(methodOn(ProductController.class).getShippingTypesByDestination(null, null, product.getProductDestinationCode())).withRel("shippingTypes"));
		
		return sdr;
	}
}
