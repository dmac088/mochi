package io.nzbee.domain.promotion;

import java.util.Date;

public abstract class Promotion {
	
	private String promotionCode;
	
	private String promotionDesc;
	
	private Date promotionStartDt;
	
	private Date promotionEndDt;
	
	private PromotionType promotionType;

	public Promotion(String promotionCode, 
					 String promotionDesc,
					 Date promotionStartDt,
					 Date promotionEndDt,
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

	public Date getPromotionStartDt() {
		return promotionStartDt;
	}

	public Date getPromotionEndDt() {
		return promotionEndDt;
	}

	public PromotionType getPromotionType() {
		return promotionType;
	}
	
}
