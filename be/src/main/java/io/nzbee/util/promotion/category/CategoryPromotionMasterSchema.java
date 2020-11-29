package io.nzbee.util.promotion.category;


public class CategoryPromotionMasterSchema {
	
	private String PROMOTION_CODE;
	
	private String CATEGORY_CODE;
	
	public String get_PROMOTION_CODE() {
		return PROMOTION_CODE;
	}

	public void set_PROMOTION_CODE(String pROMOTION_CODE) {
		PROMOTION_CODE = pROMOTION_CODE;
	}

	public String get_CATEGORY_CODE() {
		return CATEGORY_CODE;
	}

	public void set_CATEGORY_CODE(String cATEGORY_CODE) {
		CATEGORY_CODE = cATEGORY_CODE;
	}

	@Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(", PROMOTION_CODE=").append(PROMOTION_CODE)
               .append("]");
        return builder.toString();    
    }

}
