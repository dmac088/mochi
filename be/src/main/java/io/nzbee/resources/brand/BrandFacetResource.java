package io.nzbee.resources.brand;

import org.springframework.hateoas.RepresentationModel;
import io.nzbee.view.product.brand.facet.BrandFacetView;

public class BrandFacetResource extends RepresentationModel<BrandFacetResource>  {
	
	private final BrandFacetView data;
	
	public BrandFacetResource(BrandFacetView brand) {
		this.data = brand;
	}

	public BrandFacetView getData() {
		return data;
	}
	
}
