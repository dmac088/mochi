package io.nzbee.resources.category.facet;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;
import io.nzbee.search.facet.EntityFacet;

@Relation(collectionRelation="categories", itemRelation="category")
public class CategoryFacetModel extends RepresentationModel<CategoryFacetModel>  {
	
	private final EntityFacet data;
	
	public CategoryFacetModel(EntityFacet category) {
		this.data = category;
	}

	public EntityFacet getData() {
		return data;
	}
	
}
