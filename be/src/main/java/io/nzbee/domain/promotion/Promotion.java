package io.nzbee.domain.promotion;

import java.time.LocalDateTime;

//Anemic
public class Promotion {
	
	private String promotionCode;
	
	private String promotionDesc;
	
	private LocalDateTime promotionStartDt;
	
	private LocalDateTime promotionEndDt;
	
	private PromotionType promotionType;

	public Promotion(String promotionCode, 
					 String promotionDesc,
					 LocalDateTime promotionStartDt,
					 LocalDateTime promotionEndDt,
					 PromotionType promotionType) {
		this.promotionCode = promotionCode;
		this.promotionDesc = promotionDesc;
		this.promotionStartDt = promotionStartDt;
		this.promotionEndDt = promotionEndDt;
		this.promotionType = promotionType;
		
	}

	public String getPromotionCode() {
		return promotionCode;
	}

	public String getPromotionDesc() {
		return promotionDesc;
	}

	public LocalDateTime getPromotionStartDt() {
		return promotionStartDt;
	}

	public LocalDateTime getPromotionEndDt() {
		return promotionEndDt;
	}

	public PromotionType getPromotionType() {
		return promotionType;
	}
	
}
