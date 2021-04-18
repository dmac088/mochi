package io.nzbee.resources.category;

import org.springframework.hateoas.RepresentationModel;
import io.nzbee.view.category.product.ProductCategoryView;

public class CategoryResource extends RepresentationModel<CategoryResource>  {
	
	private final ProductCategoryView data;
	
	public CategoryResource(ProductCategoryView category) {
		this.data = category;
	}

	public ProductCategoryView getData() {
		return data;
	}
	
}
