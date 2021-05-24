package io.nzbee.resources.product.physical.light;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import io.nzbee.resources.controllers.ProductController;
import io.nzbee.view.product.physical.light.PhysicalProductLightView;

@Component
public class PhysicalProductLightModelAssembler extends RepresentationModelAssemblerSupport<PhysicalProductLightView, PhysicalProductLightModel> {
	
	public PhysicalProductLightModelAssembler() {
		super(ProductController.class, PhysicalProductLightModel.class);
	}

	@Override
	public PhysicalProductLightModel toModel(PhysicalProductLightView product) {
		PhysicalProductLightModel pr = new PhysicalProductLightModel(product);

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
