package io.nzbee.resources.product;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import io.nzbee.resources.controllers.ProductController;
import io.nzbee.view.product.physical.PhysicalProductDTOLight;

@Component
public class ProductLightResourceAssembler extends RepresentationModelAssemblerSupport<PhysicalProductDTOLight, ProductLightResource> {
	
	public ProductLightResourceAssembler() {
		super(ProductController.class, ProductLightResource.class);
	}

	@Override
	public ProductLightResource toModel(PhysicalProductDTOLight product) {
		ProductLightResource pr = new ProductLightResource(product);

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
