package io.nzbee.resources.product;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import io.nzbee.dto.product.ProductDTOFull;
import io.nzbee.resources.controllers.ProductController;

@Component
public class ProductFullResourceAssembler extends RepresentationModelAssemblerSupport<ProductDTOFull, ProductFullResource> {
	
	public ProductFullResourceAssembler() {
		super(ProductController.class, ProductFullResource.class);
	}

	@Override
	public ProductFullResource toModel(ProductDTOFull product) {
		ProductFullResource pr = new ProductFullResource(product);

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
