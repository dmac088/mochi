package io.nzbee.util.promotion.type;


public class PromotionTypeMasterSchema {
	
	private String PROMOTION_TYPE_CODE;
	
	private String PROMOTION_TYPE_DESC;

	
	public String get_PROMOTION_TYPE_CODE() {
		return PROMOTION_TYPE_CODE;
	}


	public void set_PROMOTION_TYPE_CODE(String PROMOTION_TYPE_CODE) {
		this.PROMOTION_TYPE_CODE = PROMOTION_TYPE_CODE;
	}


	public String get_PROMOTION_TYPE_DESC() {
		return this.PROMOTION_TYPE_DESC;
	}


	public void set_PROMOTION_TYPE_DESC(String PROMOTION_TYPE_DESC) {
		this.PROMOTION_TYPE_DESC = PROMOTION_TYPE_DESC;
	}
	
	@Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(", PROMOTION_TYPE_CODE=").append(PROMOTION_TYPE_CODE)
               .append("]");
        return builder.toString();
        
    }
	
}
