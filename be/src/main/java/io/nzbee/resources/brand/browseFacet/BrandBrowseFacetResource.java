package io.nzbee.resources.brand.browseFacet;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.nzbee.view.product.brand.facet.BrandFacetView;

public class BrandBrowseFacetResource extends RepresentationModel<BrandBrowseFacetResource> {

	private final BrandFacetView data;
	
	@JsonCreator
	public BrandBrowseFacetResource(@JsonProperty("brand") BrandFacetView brand) {
		this.data = brand;
		
	}
	
	public BrandFacetView getData() {
		return data;
	}
}
