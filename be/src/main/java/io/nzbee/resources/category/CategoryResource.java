package io.nzbee.resources.category;

import org.springframework.hateoas.ResourceSupport;

import io.nzbee.domain.category.Category;

public class CategoryResource  extends ResourceSupport  {
	
	private final Category data;
	
	public CategoryResource(Category category) {
		this.data = category;
	}

	public Category getData() {
		return data;
	}
	
}
