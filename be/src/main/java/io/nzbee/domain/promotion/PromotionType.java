package io.nzbee.domain.promotion;

public class PromotionType {

	private String promotionTypeCode;
	
	private String promotionTypeDesc;

	public PromotionType(String promotionTypeCode, String promotionTypeDesc) {
		super();
		this.promotionTypeCode = promotionTypeCode;
		this.promotionTypeDesc = promotionTypeDesc;
	}

	public String getPromotionTypeCode() {
		return promotionTypeCode;
	}

	public String getPromotionTypeDesc() {
		return promotionTypeDesc;
	}
	
}
