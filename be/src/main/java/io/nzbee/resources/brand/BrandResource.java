package io.nzbee.resources.brand;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.nzbee.view.product.brand.BrandDTO;

public class BrandResource extends RepresentationModel<BrandResource> {

	private final BrandDTO data;
	
	@JsonCreator
	public BrandResource(@JsonProperty("brand") BrandDTO brand) {
		this.data = brand;
		
	}
	
	public BrandDTO getData() {
		return data;
	}
}
