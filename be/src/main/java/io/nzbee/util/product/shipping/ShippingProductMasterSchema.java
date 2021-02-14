package io.nzbee.util.product.shipping;

import io.nzbee.util.product.ProductMasterSchema;

public class ShippingProductMasterSchema extends ProductMasterSchema {

	private String SERVICE_TYPE_CODE;
	
	private String ZONE_CODE;
	
	private String DESTINATION_CODE;
	
	private String WEIGHT_LIMIT;
	
	private String TRACKING_LEVEL;
	
	private String AMOUNT_HKD;
	

	public String get_SERVICE_TYPE_CODE() {
		return SERVICE_TYPE_CODE;
	}

	public void set_SERVICE_TYPE_CODE(String sERVICE_TYPE_CODE) {
		SERVICE_TYPE_CODE = sERVICE_TYPE_CODE;
	}

	public String get_ZONE_CODE() {
		return ZONE_CODE;
	}

	public void set_ZONE_CODE(String zONE_CODE) {
		ZONE_CODE = zONE_CODE;
	}

	public String get_DESTINATION_CODE() {
		return DESTINATION_CODE;
	}

	public void set_DESTINATION_CODE(String dESTINATION_CODE) {
		DESTINATION_CODE = dESTINATION_CODE;
	}

	public String get_WEIGHT_LIMIT() {
		return WEIGHT_LIMIT;
	}

	public void set_WEIGHT_LIMIT(String wEIGHT_LIMIT) {
		WEIGHT_LIMIT = wEIGHT_LIMIT;
	}

	public String get_TRACKING_LEVEL() {
		return TRACKING_LEVEL;
	}

	public void set_TRACKING_LEVEL(String tRACKING_LEVEL) {
		TRACKING_LEVEL = tRACKING_LEVEL;
	}

	public String get_AMOUNT_HKD() {
		return AMOUNT_HKD;
	}

	public void set_AMOUNT_HKD(String aMOUNT_HKD) {
		AMOUNT_HKD = aMOUNT_HKD;
	}

	@Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("ShippingProductMasterSchema [PRODUCT_UPC_CODE=").append(PRODUCT_UPC_CODE)
        	   .append(", PRODUCT_CREATED_DATE=").append(PRODUCT_CREATED_DATE)
        	   .append(", PRODUCT_DESCRIPTION_EN=").append(PRODUCT_DESCRIPTION_EN)
        	   .append(", PRODUCT_DESCRIPTION_HK=").append(PRODUCT_DESCRIPTION_HK)
        	   .append(", BRAND_CODE=").append(BRAND_CODE)
        	   .append(", BRAND_DESCRIPTION_EN=").append(BRAND_DESCRIPTION_EN)
        	   .append(", BRAND_DESCRIPTION_HK=").append(BRAND_DESCRIPTION_HK)
        	   .append(", PRODUCT_RETAIL_PRICE_USD=").append(PRODUCT_RETAIL_PRICE_USD)
        	   .append(", PRODUCT_MARKDOWN_PRICE_USD=").append(PRODUCT_MARKDOWN_PRICE_USD)
        	   .append(", PRODUCT_RETAIL_PRICE_HKD=").append(PRODUCT_RETAIL_PRICE_HKD)
        	   .append(", PRODUCT_MARKDOWN_PRICE_HKD=").append(PRODUCT_MARKDOWN_PRICE_HKD)
        	   .append(", PRODUCT_IMAGE_EN=").append(PRODUCT_IMAGE_EN)
        	   .append(", PRODUCT_IMAGE_HK=").append(PRODUCT_IMAGE_HK)
        	   .append(", PRIMARY_CATEGORY_CODE=").append(PRIMARY_CATEGORY_CODE)
        	   .append(", SERVICE_TYPE_CODE=").append(SERVICE_TYPE_CODE)
        	   .append(", ZONE_CODE=").append(ZONE_CODE)
        	   .append(", DESTINATION_CODE=").append(DESTINATION_CODE)
        	   .append(", WEIGHT_LIMIT=").append(WEIGHT_LIMIT)
        	   .append(", TRACKING_LEVEL=").append(TRACKING_LEVEL)
        	   .append(", AMOUNT_HKD=").append(AMOUNT_HKD)
               .append("]");
        return builder.toString();
        
    }
}
