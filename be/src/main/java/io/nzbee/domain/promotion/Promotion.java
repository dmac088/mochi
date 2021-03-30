package io.nzbee.domain.promotion;

import java.time.LocalDateTime;

//Anemic
public class Promotion {
	
	private String promotionCode;
	
	private String promotionDesc;
	
	private LocalDateTime promotionStartDt;
	
	private LocalDateTime promotionEndDt;
	
	private String promotionTypeCode;
	
	private String promotionTypeDesc;	

	public Promotion(String promotionCode, 
					 String promotionDesc,
					 LocalDateTime promotionStartDt,
					 LocalDateTime promotionEndDt,
					 String promotionTypeCode,
					 String promotionTypeDesc) {
		this.promotionCode 		= promotionCode;
		this.promotionDesc 		= promotionDesc;
		this.promotionStartDt 	= promotionStartDt;
		this.promotionEndDt 	= promotionEndDt;
		this.promotionTypeCode 	= promotionTypeCode;
		this.promotionTypeDesc 	= promotionTypeDesc;
		
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

	public String getPromotionTypeCode() {
		return promotionTypeCode;
	}

	public String getPromotionTypeDesc() {
		return promotionTypeDesc;
	}
	
}
