package io.nzbee.exceptions;

public class CategoryNotFoundResponse {

	private String categoryNotFound;

	public CategoryNotFoundResponse(String categoryNotFound) {
		this.setCategoryNotFound(categoryNotFound);
	}

	public String getCategoryNotFound() {
		return categoryNotFound;
	}

	public void setCategoryNotFound(String categoryNotFound) {
		this.categoryNotFound = categoryNotFound;
	}
	
}
