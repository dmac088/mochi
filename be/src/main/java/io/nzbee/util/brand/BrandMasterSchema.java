package io.nzbee.util.brand;


public class BrandMasterSchema {
	
	private String BRAND_CODE;
	
	private String BRAND_DESC_EN;
	
	private String BRAND_DESC_HK;
	
	
	public String get_BRAND_CODE() {
		return BRAND_CODE;
	}

	public void set_BRAND_CODE(String BRAND_CODE) {
		this.BRAND_CODE = BRAND_CODE;
	}

	public String get_BRAND_DESC_EN() {
		return BRAND_DESC_EN;
	}

	public void set_BRAND_DESC_EN(String pRIMARY_BRAND_DESC_EN) {
		BRAND_DESC_EN = pRIMARY_BRAND_DESC_EN;
	}

	public String get_BRAND_DESC_HK() {
		return BRAND_DESC_HK;
	}

	public void set_BRAND_DESC_HK(String pRIMARY_BRAND_DESC_HK) {
		BRAND_DESC_HK = pRIMARY_BRAND_DESC_HK;
	}
	
	
	@Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(", BRAND_CODE=").append(BRAND_CODE)
               .append("]");
        return builder.toString();
        
    }
	
}
