package io.nzbee.resources.brand.search.facet;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;
import io.nzbee.search.facet.EntityFacet;

@Relation(collectionRelation="brands", itemRelation="brand")
public class BrandSearchFacetModel extends RepresentationModel<BrandSearchFacetModel>  {
	
	private final EntityFacet data;
	
	public BrandSearchFacetModel(EntityFacet brand) {
		this.data = brand;
	}

	public EntityFacet getData() {
		return data;
	}
	
}
