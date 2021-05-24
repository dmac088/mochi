package io.nzbee.resources.brand.facet;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;
import io.nzbee.search.facet.EntityFacet;

@Relation(collectionRelation="brands", itemRelation="brand")
public class BrandFacetModel extends RepresentationModel<BrandFacetModel>  {
	
	private final EntityFacet data;
	
	public BrandFacetModel(EntityFacet brand) {
		this.data = brand;
	}

	public EntityFacet getData() {
		return data;
	}
	
}
