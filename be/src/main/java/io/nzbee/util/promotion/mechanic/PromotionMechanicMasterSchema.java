package io.nzbee.util.promotion.mechanic;


public class PromotionMechanicMasterSchema {
	
	private String PROMOTION_MECHANIC_CODE;
	
	private String PROMOTION_MECHANIC_DESC;
	
		
	@Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(", PROMOTION_MECHANIC_CODE=").append(PROMOTION_MECHANIC_CODE)
               .append("]");
        return builder.toString();
        
    }


	public String get_PROMOTION_MECHANIC_DESC() {
		return this.PROMOTION_MECHANIC_DESC;
	}


	public void set_PROMOTION_MECHANIC_DESC(String PROMOTION_MECHANIC_DESC) {
		this.PROMOTION_MECHANIC_DESC = PROMOTION_MECHANIC_DESC;
	}
	
}
