package io.nzbee.util;


public class CategoryMasterSchema {
	
	private String CATEGORY_CODE;
	
	private String PARENT_CATEGORY_CODE;
	
	private String CATEGORY_DESC_EN;
	
	private String CATEGORY_DESC_HK;
	
	public String get_CATEGORY_CODE() {
		return CATEGORY_CODE;
	}

	public void set_CATEGORY_CODE(String CATEGORY_CODE) {
		this.CATEGORY_CODE = CATEGORY_CODE;
	}
	
	public String get_PARENT_CATEGORY_CODE() {
		return PARENT_CATEGORY_CODE;
	}

	public void set_PARENT_CATEGORY_CODE(String pARENT_CATEGORY_CODE) {
		PARENT_CATEGORY_CODE = pARENT_CATEGORY_CODE;
	}

	public String get_CATEGORY_DESC_EN() {
		return CATEGORY_DESC_EN;
	}

	public void set_CATEGORY_DESC_EN(String pRIMARY_CATEGORY_DESC_EN) {
		CATEGORY_DESC_EN = pRIMARY_CATEGORY_DESC_EN;
	}

	public String get_CATEGORY_DESC_HK() {
		return CATEGORY_DESC_HK;
	}

	public void set_CATEGORY_DESC_HK(String pRIMARY_CATEGORY_DESC_HK) {
		CATEGORY_DESC_HK = pRIMARY_CATEGORY_DESC_HK;
	}
	
	
	@Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(", CATEGORY_CODE=").append(CATEGORY_CODE)
               .append("]");
        return builder.toString();
        
    }
	
}
