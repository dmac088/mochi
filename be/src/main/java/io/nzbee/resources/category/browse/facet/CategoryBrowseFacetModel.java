package io.nzbee.resources.category.browse.facet;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.nzbee.view.category.product.ProductCategoryView;

@Relation(collectionRelation="categories", itemRelation="category")
public class CategoryBrowseFacetModel extends RepresentationModel<CategoryBrowseFacetModel>  {
	
	private final ProductCategoryView data;
	
	public CategoryBrowseFacetModel(ProductCategoryView category) {
		this.data = category;
	}

	public ProductCategoryView getData() {
		return data;
	}
	
}
