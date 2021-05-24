package io.nzbee.resources.brand;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.nzbee.view.product.brand.BrandView;

@Relation(collectionRelation="brands", itemRelation="brand")
public class BrandViewModel extends RepresentationModel<BrandViewModel> {

	private final BrandView data;
	
	@JsonCreator
	public BrandViewModel(@JsonProperty("brand") BrandView brand) {
		this.data = brand;
		
	}
	
	public BrandView getData() {
		return data;
	}
}
