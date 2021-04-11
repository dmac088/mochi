package io.nzbee.resources.brand;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import io.nzbee.resources.brand.BrandViewResource;
import io.nzbee.resources.controllers.BrandController;
import io.nzbee.view.product.brand.BrandView;

@Component
public class BrandViewResourceAssembler extends RepresentationModelAssemblerSupport<BrandView, BrandViewResource> {

	public BrandViewResourceAssembler() {
		super(BrandController.class, BrandViewResource.class);
	}


	@Override
	public BrandViewResource toModel(BrandView brand) {
		BrandViewResource br = new BrandViewResource(brand);
		br.add(linkTo(methodOn(BrandController.class).get(	brand.getLocale(),
															brand.getBrandCode())).withSelfRel());
		return br;
	}

}
