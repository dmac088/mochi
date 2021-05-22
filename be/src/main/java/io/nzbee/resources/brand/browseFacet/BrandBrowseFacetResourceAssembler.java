package io.nzbee.resources.brand.browseFacet;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import io.nzbee.resources.controllers.BrandController;
import io.nzbee.view.product.brand.facet.BrandFacetView;

@Component
public class BrandBrowseFacetResourceAssembler extends RepresentationModelAssemblerSupport<BrandFacetView, BrandBrowseFacetResource> {

	public BrandBrowseFacetResourceAssembler() {
		super(BrandController.class, BrandBrowseFacetResource.class);
	}


	@Override
	public BrandBrowseFacetResource toModel(BrandFacetView brand) {
		BrandBrowseFacetResource br = new BrandBrowseFacetResource(brand);
		return br;
	}

}
