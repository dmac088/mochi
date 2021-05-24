package io.nzbee.resources.category.facet;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;
import io.nzbee.search.facet.EntityFacetHierarchical;

@Relation(collectionRelation="categories", itemRelation="category")
public class CategoryFacetModel extends RepresentationModel<CategoryFacetModel>  {
	
	private final EntityFacetHierarchical data;
	
	public CategoryFacetModel(EntityFacetHierarchical category) {
		this.data = category;
	}

	public EntityFacetHierarchical getData() {
		return data;
	}
	
}
