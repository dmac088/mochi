package io.nzbee.resources.brand;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.nzbee.view.product.brand.BrandFacetView;

public class BrandResource extends RepresentationModel<BrandResource> {

	private final BrandFacetView data;
	
	@JsonCreator
	public BrandResource(@JsonProperty("brand") BrandFacetView brand) {
		this.data = brand;
		
	}
	
	public BrandFacetView getData() {
		return data;
	}
}
