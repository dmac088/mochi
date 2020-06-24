package io.nzbee.resources.category;

import org.springframework.hateoas.RepresentationModel;
import io.nzbee.domain.category.Category;

public class CategoryResource extends RepresentationModel<CategoryResource>  {
	
	private final Category data;
	
	public CategoryResource(Category category) {
		this.data = category;
	}

	public Category getData() {
		return data;
	}
	
}
