package io.nzbee.resources.product;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import io.nzbee.dto.product.ProductDTOLight;
import io.nzbee.resources.controllers.CategoryController;
import io.nzbee.resources.controllers.ProductController;

@Component
public class ProductLightResourceAssembler extends RepresentationModelAssemblerSupport<ProductDTOLight, ProductLightResource> {
	
	public ProductLightResourceAssembler() {
		super(ProductController.class, ProductLightResource.class);
	}

	@Override
	public ProductLightResource toModel(ProductDTOLight product) {
		ProductLightResource pr = new ProductLightResource(product);

		pr.add(linkTo(methodOn(ProductController.class).get(product.getLocale(), product.getCurrency(),
				product.getProductUPC())).withSelfRel(),

				linkTo(methodOn(CategoryController.class).getCategories(product.getLocale(), product.getProductUPC()))
						.withRel("categories"),
						
				linkTo(methodOn(ProductController.class).getImageWithMediaType(product.getProductUPC() + "_1.jpg"))
						.withRel("defaultImage"),
						
				linkTo(methodOn(ProductController.class).getImageWithMediaType(null))
						.withRel("images")
				);

		return pr;
	}
}
