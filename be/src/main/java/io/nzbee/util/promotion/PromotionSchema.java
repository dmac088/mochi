package io.nzbee.util.promotion;


public class PromotionSchema {
	
	protected String PROMOTION_CODE;
	
	protected String PROMOTION_DESC_HK;
	
	protected String PROMOTION_DESC_EN;
	
	protected String PROMOTION_START_DATE;
	
	protected String PROMOTION_END_DATE;
	
	protected Boolean PROMOTION_ACTIVE;
	
	protected String PROMOTION_MECHANIC_CODE;
	
	protected String PROMOTION_TYPE_CODE;
	
	
	public String get_PROMOTION_CODE() {
		return PROMOTION_CODE;
	}

	public void set_PROMOTION_CODE(String PROMOTION_CODE) {
		this.PROMOTION_CODE = PROMOTION_CODE;
	}
	
	public String get_PROMOTION_DESC_HK() {
		return PROMOTION_DESC_HK;
	}

	public void set_PROMOTION_DESC_HK(String PROMOTION_DESC) {
		 this.PROMOTION_DESC_HK = PROMOTION_DESC;
	}

	public String get_PROMOTION_DESC_EN() {
		return PROMOTION_DESC_EN;
	}

	public void set_PROMOTION_DESC_EN(String pROMOTION_DESC_EN) {
		PROMOTION_DESC_EN = pROMOTION_DESC_EN;
	}

	public String get_PROMOTION_START_DATE() {
		return this.PROMOTION_START_DATE;
	}

	public void set_PROMOTION_START_DATE(String PROMOTION_START_DATE) {
		 this.PROMOTION_START_DATE = PROMOTION_START_DATE;
	}

	public String get_PROMOTION_END_DATE() {
		return  this.PROMOTION_END_DATE;
	}

	public void set_PROMOTION_END_DATE(String PROMOTION_END_DATE) {
		 this.PROMOTION_END_DATE = PROMOTION_END_DATE;
	}

	public String get_PROMOTION_MECHANIC_CODE() {
		return  this.PROMOTION_MECHANIC_CODE;
	}

	public void set_PROMOTION_MECHANIC_CODE(String PROMOTION_MECHANIC_CODE) {
		 this.PROMOTION_MECHANIC_CODE = PROMOTION_MECHANIC_CODE;
	}

	public String get_PROMOTION_TYPE_CODE() {
		return PROMOTION_TYPE_CODE;
	}

	public void set_PROMOTION_TYPE_CODE(String PROMOTION_TYPE_CODE) {
		this.PROMOTION_TYPE_CODE = PROMOTION_TYPE_CODE;
	}
	
	public Boolean get_PROMOTION_ACTIVE() {
		return PROMOTION_ACTIVE;
	}

	public void set_PROMOTION_ACTIVE(String PROMOTION_ACTIVE) {
		this.PROMOTION_ACTIVE = PROMOTION_ACTIVE.toLowerCase().equals("true");
	}

	@Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(", PROMOTION_CODE=").append(PROMOTION_CODE)
               .append("]");
        return builder.toString();    
    }

}
