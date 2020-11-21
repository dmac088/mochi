package io.nzbee.domain.promotion;

public class Promotion {
	
	private String promotionCode;
	
	private String promotionDesc;

	public Promotion(String promotionCode, String promotionDesc) {
		super();
		this.promotionCode = promotionCode;
		this.promotionDesc = promotionDesc;
	}

	public String getPromotionCode() {
		return promotionCode;
	}

	public String getPromotionDesc() {
		return promotionDesc;
	}

}
