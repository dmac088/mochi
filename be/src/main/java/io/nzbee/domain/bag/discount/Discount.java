package io.nzbee.domain.bag.discount;

import io.nzbee.domain.promotion.Promotion;

public class Discount {

	private Promotion promotion;
	
	private Double discountAmount; 
	
	public Discount(Promotion promotion, Double discount) {
		this.promotion = promotion;
		this.discountAmount = discount;
	}

	public Promotion getPromotion() {
		return promotion;
	}

	public Double getDiscountAmount() {
		return discountAmount;
	}
	
}
