package io.nzbee.resources.brand;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import io.nzbee.domain.brand.Brand;
import io.nzbee.resources.controllers.BrandController;

@Component
public class BrandResourceAssembler extends ResourceAssemblerSupport<Brand, BrandResource> {

	public BrandResourceAssembler() {
		super(BrandController.class, BrandResource.class);
	}

	@Override
	public BrandResource toResource(Brand brand) {
		BrandResource br = new BrandResource(brand);
		br.add(linkTo(methodOn(BrandController.class).get(	brand.getLocale(),
															brand.getCurrency(),
															brand.getCode())).withSelfRel());
		return br;
	}

}
