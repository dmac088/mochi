package io.nzbee.domain.promotion;

import java.time.LocalDateTime;

public class OrderPromotion extends Promotion {
	
	private String couponCode; 

	public OrderPromotion(String promotionCode, 
						  String promotionDesc, 
						  LocalDateTime promotionStartDt,
						  LocalDateTime promotionEndDt, 
						  String promotionMechanicCode, 
						  String promotionMechanicDesc,
						  String promotionTypeCode, 
						  String promotionTypeDesc,
						  String couponCode) {
		super(promotionCode, promotionDesc, promotionStartDt, promotionEndDt, promotionMechanicCode, promotionMechanicDesc,
				promotionTypeCode, promotionTypeDesc);
		
		this.couponCode = couponCode;
	}

	public String getCouponCode() {
		return couponCode;
	}

}
