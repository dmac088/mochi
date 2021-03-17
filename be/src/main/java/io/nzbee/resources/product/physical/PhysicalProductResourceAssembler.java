package io.nzbee.resources.product.physical;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import io.nzbee.resources.controllers.ProductController;
import io.nzbee.view.product.physical.PhysicalProductDTO;

@Component
public class PhysicalProductResourceAssembler extends RepresentationModelAssemblerSupport<PhysicalProductDTO, PhysicalProductResource> {
	
	public PhysicalProductResourceAssembler() {
		super(ProductController.class, PhysicalProductResource.class);
	}

	@Override
	public PhysicalProductResource toModel(PhysicalProductDTO product) {
		PhysicalProductResource pr = new PhysicalProductResource(product);

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
