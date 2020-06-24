package io.nzbee.resources.brand;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.nzbee.domain.brand.Brand;

public class BrandResource extends RepresentationModel<BrandResource> {

	private final Brand data;
	
	@JsonCreator
	public BrandResource(@JsonProperty("brand") Brand brand) {
		this.data = brand;
		
	}
	
	public Brand getData() {
		return data;
	}
}
