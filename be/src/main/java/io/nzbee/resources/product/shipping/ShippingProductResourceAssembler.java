package io.nzbee.resources.product.shipping;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import io.nzbee.resources.controllers.ProductController;
import io.nzbee.view.product.shipping.ShippingProductView;

@Component
public class ShippingProductResourceAssembler extends RepresentationModelAssemblerSupport<ShippingProductView, ShippingProductResource> {
	
	public ShippingProductResourceAssembler() {
		super(ProductController.class, ShippingProductResource.class);
	}

	@Override
	public ShippingProductResource toModel(ShippingProductView product) {
		ShippingProductResource pr = new ShippingProductResource(product);

		pr.add(linkTo(methodOn(ProductController.class).get(null, null,
				product.getProductUPC())).withSelfRel(),

				linkTo(methodOn(ProductController.class).getImageWithMediaType(product.getProductUPC() + "_1.jpg"))
						.withRel("defaultImage"),
						
				linkTo(methodOn(ProductController.class).getImageWithMediaType(null))
						.withRel("images")
				);

		return pr;
	}
}
