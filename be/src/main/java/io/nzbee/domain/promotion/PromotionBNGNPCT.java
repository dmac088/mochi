package io.nzbee.domain.promotion;
import java.time.LocalDateTime;


public class PromotionBNGNPCT extends Promotion {
	
	private int buyQuantity;
	
	private Double discountPercentage;

	public PromotionBNGNPCT(		String promotionCode, 
									String promotionDesc, 
									LocalDateTime promotionStartDt, 
									LocalDateTime promotionEndDt,
									PromotionType promotionType,
									int buyQuantity,
									Double discountPercentage) {
		super(promotionCode, "Buy " + buyQuantity + " get " + discountPercentage + " off", promotionStartDt, promotionEndDt, promotionType);
		this.buyQuantity = buyQuantity;
		this.discountPercentage = discountPercentage;
	}

	public int getBuyQuantity() {
		return buyQuantity;
	}

	public Double getFreeQuantity() {
		return discountPercentage;
	}

	
}
