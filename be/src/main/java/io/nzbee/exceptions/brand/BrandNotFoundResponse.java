package io.nzbee.exceptions.brand;

public class BrandNotFoundResponse {

	private String categoryNotFound;

	public BrandNotFoundResponse(String categoryNotFound) {
		this.setCategoryNotFound(categoryNotFound);
	}

	public String getCategoryNotFound() {
		return categoryNotFound;
	}

	public void setCategoryNotFound(String categoryNotFound) {
		this.categoryNotFound = categoryNotFound;
	}
	
}
