package io.nzbee.resources.brand.browse.facet;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.nzbee.view.product.brand.facet.BrandFacetView;

@Relation(collectionRelation="brands", itemRelation="brand")
public class BrandBrowseFacetModel extends RepresentationModel<BrandBrowseFacetModel> {

	private final BrandFacetView data;
	
	@JsonCreator
	public BrandBrowseFacetModel(@JsonProperty("brand") BrandFacetView brand) {
		this.data = brand;
		
	}
	
	public BrandFacetView getData() {
		return data;
	}
}
