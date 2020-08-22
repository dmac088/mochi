package io.nzbee.resources.product;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import io.nzbee.Globals;
import io.nzbee.domain.product.Product;
import io.nzbee.resources.controllers.CategoryController;
import io.nzbee.resources.controllers.ProductController;

@Component
public class ProductResourceAssembler extends RepresentationModelAssemblerSupport<Product, ProductResource> {
//
//	@Autowired
//	private Globals globalVars;
	
	public ProductResourceAssembler() {
		super(ProductController.class, ProductResource.class);
	}

	@Override
	public ProductResource toModel(Product product) {
		ProductResource pr = new ProductResource(product);

		pr.add(linkTo(methodOn(ProductController.class).get(product.getLclCd(), product.getCurrency(),
				product.getProductUPC())).withSelfRel(),

				linkTo(methodOn(CategoryController.class).getCategories(product.getLclCd(), product.getProductUPC()))
						.withRel("categories"),
						
				linkTo(methodOn(ProductController.class).getImageWithMediaType(product.getProductUPC() + "_1.jpg"))
						.withRel("defaultImage"),
						
				linkTo(methodOn(ProductController.class).getImageWithMediaType(null))
						.withRel("images")
				);

		return pr;
	}
}
