package io.nzbee.util.promotion;


public class PromotionMasterSchema {
	
	private String PROMOTION_CODE;
	
	private String PROMOTION_DESC;
	
	private String PROMOTION_START_DATE;
	
	private String PROMOTION_END_DATE;
	
	private String PROMOTION_ACTIVE;
	
	private String PROMOTION_MECHANIC_CODE;
	
	private String PROMOTION_TYPE_CODE;
	
	
	public String get_PROMOTION_CODE() {
		return PROMOTION_CODE;
	}

	public void set_PROMOTION_CODE(String PROMOTION_CODE) {
		this.PROMOTION_CODE = PROMOTION_CODE;
	}
	
	public String get_PROMOTION_DESC() {
		return PROMOTION_DESC;
	}

	public void set_PROMOTION_DESC(String PROMOTION_DESC) {
		 this.PROMOTION_DESC = PROMOTION_DESC;
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
	
	public String get_PROMOTION_ACTIVE() {
		return PROMOTION_ACTIVE;
	}

	public void set_PROMOTION_ACTIVE(String PROMOTION_ACTIVE) {
		this.PROMOTION_ACTIVE = PROMOTION_ACTIVE;
	}

	@Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(", PROMOTION_CODE=").append(PROMOTION_CODE)
               .append("]");
        return builder.toString();    
    }

}