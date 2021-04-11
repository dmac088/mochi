package io.nzbee.resources.brand;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import io.nzbee.resources.controllers.BrandController;
import io.nzbee.view.product.brand.BrandFacetView;

@Component
public class BrandResourceAssembler extends RepresentationModelAssemblerSupport<BrandFacetView, BrandResource> {

	public BrandResourceAssembler() {
		super(BrandController.class, BrandResource.class);
	}


	@Override
	public BrandResource toModel(BrandFacetView brand) {
		BrandResource br = new BrandResource(brand);
		br.add(linkTo(methodOn(BrandController.class).get(	brand.getLocale(),
															brand.getBrandCode())).withSelfRel());
		return br;
	}

}
