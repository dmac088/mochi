package io.nzbee.resources.brand;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.nzbee.view.product.brand.BrandView;

public class BrandViewResource extends RepresentationModel<BrandViewResource> {

	private final BrandView data;
	
	@JsonCreator
	public BrandViewResource(@JsonProperty("brand") BrandView brand) {
		this.data = brand;
		
	}
	
	public BrandView getData() {
		return data;
	}
}