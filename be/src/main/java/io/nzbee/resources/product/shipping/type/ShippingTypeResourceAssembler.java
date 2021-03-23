package io.nzbee.resources.product.shipping.type;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import io.nzbee.resources.controllers.ProductController;
import io.nzbee.view.product.shipping.type.ShippingTypeDTO;

@Component
public class ShippingTypeResourceAssembler extends RepresentationModelAssemblerSupport<ShippingTypeDTO, ShippingTypeResource> {
	
	public ShippingTypeResourceAssembler() {
		super(ProductController.class, ShippingTypeResource.class);
	}

	@Override
	public ShippingTypeResource toModel(ShippingTypeDTO product) {
		ShippingTypeResource pr = new ShippingTypeResource(product);

		pr.add(linkTo(methodOn(ProductController.class).getShippingType(null, null,
				product.getShippingTypeCode())).withSelfRel());
		
		return pr;
	}
}
