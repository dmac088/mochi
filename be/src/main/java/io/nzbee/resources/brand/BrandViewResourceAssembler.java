package io.nzbee.resources.brand;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
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
		return br;
	}

}
