package io.nzbee.domain.bag.discount;

import io.nzbee.domain.bag.BagItem;
import io.nzbee.domain.promotion.Promotion;

public class Discount {

	private BagItem bagItem;
	
	private Promotion promotion;
	
	private Double discountAmount; 
	
	public Discount(BagItem bagItem, Promotion promotion, Double discount) {
		this.bagItem 		= bagItem;
		this.promotion 		= promotion;
		this.discountAmount = discount;
	}

	public Promotion getPromotion() {
		return promotion;
	}

	public Double getDiscountAmount() {
		return discountAmount;
	}

	public BagItem getBagItem() {
		return bagItem;
	}

}
