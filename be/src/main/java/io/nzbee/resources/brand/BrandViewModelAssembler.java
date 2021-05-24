package io.nzbee.resources.brand;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import io.nzbee.resources.controllers.BrandController;
import io.nzbee.view.product.brand.BrandView;

@Component
public class BrandViewModelAssembler extends RepresentationModelAssemblerSupport<BrandView, BrandViewModel> {

	public BrandViewModelAssembler() {
		super(BrandController.class, BrandViewModel.class);
	}


	@Override
	public BrandViewModel toModel(BrandView brand) {
		BrandViewModel br = new BrandViewModel(brand);
		return br;
	}

}
