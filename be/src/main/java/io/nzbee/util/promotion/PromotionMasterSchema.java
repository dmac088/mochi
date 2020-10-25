package io.nzbee.util.promotion;

import java.time.LocalDateTime;

public class PromotionMasterSchema {
	
	private String PROMOTION_CODE;
	
	private String PROMOTION_DESC;
	
	private LocalDateTime PROMOTION_START_DATE;
	
	private LocalDateTime PROMOTION_END_DATE;
	
	private Boolean PROMOTION_ACTIVE;
	
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

	public void set_PROMOTION_DESC(String pROMOTION_DESC) {
		PROMOTION_DESC = pROMOTION_DESC;
	}

	public LocalDateTime getPROMOTION_START_DATE() {
		return PROMOTION_START_DATE;
	}

	public void setPROMOTION_START_DATE(LocalDateTime pROMOTION_START_DATE) {
		PROMOTION_START_DATE = pROMOTION_START_DATE;
	}

	public LocalDateTime getPROMOTION_END_DATE() {
		return PROMOTION_END_DATE;
	}

	public void setPROMOTION_END_DATE(LocalDateTime pROMOTION_END_DATE) {
		PROMOTION_END_DATE = pROMOTION_END_DATE;
	}

	public String getPROMOTION_MECHANIC_CODE() {
		return PROMOTION_MECHANIC_CODE;
	}

	public void setPROMOTION_MECHANIC_CODE(String pROMOTION_MECHANIC_CODE) {
		PROMOTION_MECHANIC_CODE = pROMOTION_MECHANIC_CODE;
	}

	public String getPROMOTION_TYPE_CODE() {
		return PROMOTION_TYPE_CODE;
	}

	public void setPROMOTION_TYPE_CODE(String pROMOTION_TYPE_CODE) {
		PROMOTION_TYPE_CODE = pROMOTION_TYPE_CODE;
	}
	
	public Boolean getPROMOTION_ACTIVE() {
		return PROMOTION_ACTIVE;
	}

	public void setPROMOTION_ACTIVE(Boolean pROMOTION_ACTIVE) {
		PROMOTION_ACTIVE = pROMOTION_ACTIVE;
	}

	@Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(", PROMOTION_CODE=").append(PROMOTION_CODE)
               .append("]");
        return builder.toString();    
    }

}
