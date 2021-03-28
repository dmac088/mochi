package io.nzbee.util.promotion;


public abstract class PromotionSchema {
	
	private String PROMOTION_CODE;
	
	private String PROMOTION_DESC_HK;
	
	private String PROMOTION_DESC_EN;
	
	private String PROMOTION_START_DATE;
	
	private String PROMOTION_END_DATE;
	
	private Boolean PROMOTION_ACTIVE;
	
	private String PROMOTION_MECHANIC_CODE;
	
	private String PROMOTION_TYPE_CODE;
	
	private String PROMOTION_LEVEL_CODE;
	
	
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
	
	public String get_PROMOTION_LEVEL_CODE() {
		return PROMOTION_LEVEL_CODE;
	}

	public void set_PROMOTION_LEVEL_CODE(String pROMOTION_LEVEL_CODE) {
		PROMOTION_LEVEL_CODE = pROMOTION_LEVEL_CODE;
	}

	public String get_PROMOTION_TYPE_CODE() {
		return PROMOTION_TYPE_CODE;
	}

	public void set_PROMOTION_TYPE_CODE(String pROMOTION_TYPE_CODE) {
		PROMOTION_TYPE_CODE = pROMOTION_TYPE_CODE;
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
