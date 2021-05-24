package io.nzbee.resources.product.physical.light;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;
import io.nzbee.view.product.physical.light.PhysicalProductLightView;

@Relation(collectionRelation="products", itemRelation="product")
public class PhysicalProductLightModel  extends RepresentationModel<PhysicalProductLightModel> {

	private final PhysicalProductLightView data;
	
	public PhysicalProductLightModel(final PhysicalProductLightView product) {
		this.data = product;
	}
	
	public PhysicalProductLightView getData() {
		return data;
	}
}
