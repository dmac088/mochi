package io.nzbee.util.promotion.product;


public class ProductPromotionMasterSchema {
	
	private String PROMOTION_CODE;
	
	private String PRODUCT_UPC;
	

	public String get_PROMOTION_CODE() {
		return PROMOTION_CODE;
	}

	public void set_PROMOTION_CODE(String pROMOTION_CODE) {
		PROMOTION_CODE = pROMOTION_CODE;
	}

	public String get_PRODUCT_UPC() {
		return PRODUCT_UPC;
	}

	public void set_PRODUCT_UPC(String pRODUCT_UPC) {
		PRODUCT_UPC = pRODUCT_UPC;
	}

	@Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(", PROMOTION_CODE=").append(PROMOTION_CODE)
               .append("]");
        return builder.toString();    
    }

}
