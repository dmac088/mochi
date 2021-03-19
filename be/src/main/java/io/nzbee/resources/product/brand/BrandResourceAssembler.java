package io.nzbee.resources.product.brand;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import io.nzbee.resources.controllers.ProductController;
import io.nzbee.view.product.shipping.ShippingProductDTO;

@Component
public class BrandResourceAssembler extends RepresentationModelAssemblerSupport<ShippingProductDTO, BrandResource> {
	
	public BrandResourceAssembler() {
		super(ProductController.class, BrandResource.class);
	}

	@Override
	public BrandResource toModel(ShippingProductDTO product) {
		BrandResource pr = new BrandResource(product);

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
