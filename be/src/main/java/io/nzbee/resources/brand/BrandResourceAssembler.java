package io.nzbee.resources.brand;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import io.nzbee.domain.brand.Brand;
import io.nzbee.resources.controllers.BrandController;

@Component
public class BrandResourceAssembler extends RepresentationModelAssemblerSupport<Brand, BrandResource> {

	public BrandResourceAssembler() {
		super(BrandController.class, BrandResource.class);
	}


	@Override
	public BrandResource toModel(Brand brand) {
		BrandResource br = new BrandResource(brand);
		br.add(linkTo(methodOn(BrandController.class).get(	brand.getLocale(),
															brand.getBrandCode())).withSelfRel());
		return br;
	}

}
