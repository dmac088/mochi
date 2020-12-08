package io.nzbee.domain.promotion;

import java.util.List;
import java.util.stream.Collectors;

public class DroolsPromotionWrapper {

	private List<String> promotionCodes;

	public DroolsPromotionWrapper(List<Promotion> promotions) {
		this.promotionCodes = promotions.stream().map(p -> p.getPromotionCode()).collect(Collectors.toList());
	}

	public List<String> getPromotionCodes() {
		return promotionCodes;
	}
	
}
