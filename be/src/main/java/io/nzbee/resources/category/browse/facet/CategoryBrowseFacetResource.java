package io.nzbee.resources.category.browse.facet;

import org.springframework.hateoas.RepresentationModel;
import io.nzbee.view.category.product.ProductCategoryView;

public class CategoryBrowseFacetResource extends RepresentationModel<CategoryBrowseFacetResource>  {
	
	private final ProductCategoryView data;
	
	public CategoryBrowseFacetResource(ProductCategoryView category) {
		this.data = category;
	}

	public ProductCategoryView getData() {
		return data;
	}
	
}
