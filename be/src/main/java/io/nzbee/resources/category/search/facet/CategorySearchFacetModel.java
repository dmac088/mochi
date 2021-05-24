package io.nzbee.resources.category.search.facet;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;
import io.nzbee.search.facet.EntityFacet;

@Relation(collectionRelation="categories", itemRelation="category")
public class CategorySearchFacetModel extends RepresentationModel<CategorySearchFacetModel>  {
	
	private final EntityFacet data;
	
	public CategorySearchFacetModel(EntityFacet category) {
		this.data = category;
	}

	public EntityFacet getData() {
		return data;
	}
	
}
