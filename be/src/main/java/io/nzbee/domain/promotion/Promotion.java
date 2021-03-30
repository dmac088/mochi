package io.nzbee.domain.promotion;

import java.time.LocalDateTime;

//Anemic
public abstract class Promotion {
	
	private String promotionCode;
	
	private String promotionDesc;
	
	private LocalDateTime promotionStartDt;
	
	private LocalDateTime promotionEndDt;
	
	private String promotionMechanicCode;
	
	private String promotionMechanicDesc;	
	
	private String promotionTypeCode;
	
	private String promotionTypeDesc;	

	public Promotion(String promotionCode, 
					 String promotionDesc,
					 LocalDateTime promotionStartDt,
					 LocalDateTime promotionEndDt,
					 String promotionMechanicCode,
					 String promotionMechanicDesc,
					 String promotionTypeCode,
					 String promotionTypeDesc) {
		this.promotionCode 			= promotionCode;
		this.promotionDesc 			= promotionDesc;
		this.promotionStartDt 		= promotionStartDt;
		this.promotionEndDt 		= promotionEndDt;
		this.promotionMechanicCode 	= promotionMechanicCode;
		this.promotionMechanicDesc 	= promotionMechanicDesc;
		this.promotionTypeCode 		= promotionTypeCode;
		this.promotionTypeDesc 		= promotionTypeDesc;		
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
	
	public String getPromotionMechanicCode() {
		return promotionMechanicCode;
	}

	public String getPromotionMechanicDesc() {
		return promotionMechanicDesc;
	}

	public String getPromotionTypeCode() {
		return promotionTypeCode;
	}

	public String getPromotionTypeDesc() {
		return promotionTypeDesc;
	}

	
}
