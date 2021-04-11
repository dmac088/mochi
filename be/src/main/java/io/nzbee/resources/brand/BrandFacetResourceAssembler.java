package io.nzbee.resources.brand;


import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import io.nzbee.resources.controllers.BrandController;
import io.nzbee.view.product.brand.facet.BrandFacetView;

@Component
public class BrandFacetResourceAssembler extends RepresentationModelAssemblerSupport<BrandFacetView, BrandFacetResource> {

	public BrandFacetResourceAssembler() {
		super(BrandController.class, BrandFacetResource.class);
	}
	
	@Override
	public BrandFacetResource toModel(BrandFacetView brand) {
		return new BrandFacetResource(brand);
	}

}