package io.nzbee.util.promotion.bngnf;

import io.nzbee.util.promotion.PromotionSchema;

public class PromotionBNGNFSchema extends PromotionSchema  {
	
	private String BUY_QUANTITY;
	
	private String FREE_QUANTITY;

	public String get_BUY_QUANTITY() {
		return BUY_QUANTITY;
	}

	public void set_BUY_QUANTITY(String BUY_QUANTITY) {
		this.BUY_QUANTITY = BUY_QUANTITY;
	}

	public String get_FREE_QUANTITY() {
		return FREE_QUANTITY;
	}

	public void set_FREE_QUANTITY(String FREE_QUANTITY) {
		this.FREE_QUANTITY = FREE_QUANTITY;
	}

	@Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(", PROMOTION_CODE=").append(PROMOTION_CODE)
               .append("]");
        return builder.toString();    
    }

}
