package io.nzbee.domain.promotion.product;

import java.time.LocalDateTime;

import io.nzbee.domain.promotion.Promotion;

public class ProductPromotion extends Promotion {

	public ProductPromotion(String promotionCode, String promotionDesc, LocalDateTime promotionStartDt,
			LocalDateTime promotionEndDt, String promotionMechanicCode, String promotionMechanicDesc,
			String promotionTypeCode, String promotionTypeDesc) {
		super(promotionCode, promotionDesc, promotionStartDt, promotionEndDt, promotionMechanicCode, promotionMechanicDesc,
				promotionTypeCode, promotionTypeDesc);
		// TODO Auto-generated constructor stub
	}

}
