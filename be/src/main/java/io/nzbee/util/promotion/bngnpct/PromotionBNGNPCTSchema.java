package io.nzbee.util.promotion.bngnpct;

import io.nzbee.util.promotion.PromotionSchema;

public class PromotionBNGNPCTSchema extends PromotionSchema  {
	
	private String BUY_QUANTITY;
	
	private String PERCENT_DISCOUNT;

	public String get_BUY_QUANTITY() {
		return BUY_QUANTITY;
	}

	public void set_BUY_QUANTITY(String bUY_QUANTITY) {
		BUY_QUANTITY = bUY_QUANTITY;
	}

	public String get_PERCENT_DISCOUNT() {
		return PERCENT_DISCOUNT;
	}

	public void set_PERCENT_DISCOUNT(String pERCENT_DISCOUNT) {
		PERCENT_DISCOUNT = pERCENT_DISCOUNT;
	}

	@Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(", PROMOTION_CODE=").append(PROMOTION_CODE)
               .append("]");
        return builder.toString();    
    }

}
