package io.nzbee.resources.product;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import io.nzbee.resources.controllers.ProductController;
import io.nzbee.view.product.ProductDTO;

@Component
public class ProductResourceAssembler extends RepresentationModelAssemblerSupport<ProductDTO, ProductResource> {
	
	public ProductResourceAssembler() {
		super(ProductController.class, ProductResource.class);
	}

	@Override
	public ProductResource toModel(ProductDTO product) {
		ProductResource pr = new ProductResource(product);

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
