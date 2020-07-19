package io.nzbee.util.tag;


public class TagMasterSchema {
	
	private String TAG_CODE;
	
	private String TAG_DESC_EN;
	
	private String TAG_DESC_HK;
	
	
	public String get_TAG_CODE() {
		return TAG_CODE;
	}

	public void set_TAG_CODE(String TAG_CODE) {
		this.TAG_CODE = TAG_CODE;
	}

	public String get_TAG_DESC_EN() {
		return TAG_DESC_EN;
	}

	public void set_TAG_DESC_EN(String pRIMARY_TAG_DESC_EN) {
		TAG_DESC_EN = pRIMARY_TAG_DESC_EN;
	}

	public String get_TAG_DESC_HK() {
		return TAG_DESC_HK;
	}

	public void set_TAG_DESC_HK(String pRIMARY_TAG_DESC_HK) {
		TAG_DESC_HK = pRIMARY_TAG_DESC_HK;
	}
	
	
	@Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(", TAG_CODE=").append(TAG_CODE)
               .append("]");
        return builder.toString();
        
    }
	
}
