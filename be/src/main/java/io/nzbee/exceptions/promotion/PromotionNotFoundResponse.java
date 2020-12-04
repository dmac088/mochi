package io.nzbee.exceptions.promotion;

public class PromotionNotFoundResponse {

	private String categoryNotFound;

	public PromotionNotFoundResponse(String categoryNotFound) {
		this.setCategoryNotFound(categoryNotFound);
	}

	public String getCategoryNotFound() {
		return categoryNotFound;
	}

	public void setCategoryNotFound(String categoryNotFound) {
		this.categoryNotFound = categoryNotFound;
	}
	
}
