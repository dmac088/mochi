package io.nzbee.resources.product.physical.full;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import io.nzbee.resources.controllers.ProductController;
import io.nzbee.view.product.physical.full.PhysicalProductFullView;

@Component
public class PhysicalProductFullResourceAssembler extends RepresentationModelAssemblerSupport<PhysicalProductFullView, PhysicalProductFullResource> {
	
	public PhysicalProductFullResourceAssembler() {
		super(ProductController.class, PhysicalProductFullResource.class);
	}

	@Override
	public PhysicalProductFullResource toModel(PhysicalProductFullView product) {
		PhysicalProductFullResource pr = new PhysicalProductFullResource(product);

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
