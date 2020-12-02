package io.nzbee.domain.promotion;
import java.time.LocalDateTime;


public class PromotionBNGNPCT extends Promotion {
	
	private int buyQuantity;
	
	private int freeQuantity;

	public PromotionBNGNPCT(		String promotionCode, 
									String promotionDesc, 
									LocalDateTime promotionStartDt, 
									LocalDateTime promotionEndDt,
									PromotionType promotionType,
									int buyQuantity,
									int freeQuantity) {
		super(promotionCode, "Buy " + buyQuantity + " get " + freeQuantity + " free", promotionStartDt, promotionEndDt, promotionType);
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
