package io.nzbee.exceptions.promotion;

public class PromotionNotFoundResponse {

	private String promotionNotFound;

	public PromotionNotFoundResponse(String promotionNotFound) {
		this.setPromotionNotFound(promotionNotFound);
	}

	public String getPromotionNotFound() {
		return promotionNotFound;
	}

	public void setPromotionNotFound(String promotionNotFound) {
		this.promotionNotFound = promotionNotFound;
	}
	
}
