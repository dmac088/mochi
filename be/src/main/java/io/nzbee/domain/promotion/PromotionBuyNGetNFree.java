package io.nzbee.domain.promotion;

import java.util.Date;



public class PromotionBuyNGetNFree extends Promotion {
	
	private int buyQuantity;
	
	private int freeQuantity;

	public PromotionBuyNGetNFree(	String promotionCode, 
									String promotionDesc, 
									Date promotionStartDt, 
									Date promotionEndDt,
									PromotionType promotionType,
									int buyQuantity,
									int freeQuantity
									) {
		super(promotionCode, promotionDesc, promotionStartDt, promotionEndDt, promotionType);
		
		this.buyQuantity = buyQuantity;
		this.freeQuantity = freeQuantity;
	}

	public int getBuyQuantity() {
		return buyQuantity;
	}

	public int getFreeQuantity() {
		return freeQuantity;
	}

	
}
