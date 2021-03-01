package io.nzbee.util.product.shipping;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.nzbee.util.product.ProductMasterSchema;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ShippingProductMasterSchema extends ProductMasterSchema {

	private String SERVICE_TYPE_CODE;
	
	private String SERVICE_TYPE_NAME_EN;
	
	private String SERVICE_TYPE_NAME_HK;
	
	private String ZONE_CODE;
	
	private String DESTINATION_CODE;
	
	private String DESTINATION_NAME_EN;
	
	private String DESTINATION_NAME_HK;
	
	private String WEIGHT_LIMIT;
	
	private String WEIGHT_FROM;
	
	private String WEIGHT_TO;
	
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
	
	
	public String get_SERVICE_TYPE_NAME_EN() {
		return SERVICE_TYPE_NAME_EN;
	}

	public void set_SERVICE_TYPE_NAME_EN(String sERVICE_TYPE_NAME_EN) {
		SERVICE_TYPE_NAME_EN = sERVICE_TYPE_NAME_EN;
	}

	public String get_SERVICE_TYPE_NAME_HK() {
		return SERVICE_TYPE_NAME_HK;
	}

	public void set_SERVICE_TYPE_NAME_HK(String sERVICE_TYPE_NAME_HK) {
		SERVICE_TYPE_NAME_HK = sERVICE_TYPE_NAME_HK;
	}

	public String get_DESTINATION_NAME_EN() {
		return DESTINATION_NAME_EN;
	}

	public void set_DESTINATION_NAME_EN(String dESTINATION_NAME_EN) {
		DESTINATION_NAME_EN = dESTINATION_NAME_EN;
	}

	public String get_DESTINATION_NAME_HK() {
		return DESTINATION_NAME_HK;
	}

	public void set_DESTINATION_NAME_HK(String dESTINATION_NAME_HK) {
		DESTINATION_NAME_HK = dESTINATION_NAME_HK;
	}

	public String get_WEIGHT_FROM() {
		return WEIGHT_FROM;
	}

	public void set_WEIGHT_FROM(String wEIGHT_FROM) {
		WEIGHT_FROM = wEIGHT_FROM;
	}

	public String get_WEIGHT_TO() {
		return WEIGHT_TO;
	}

	public void set_WEIGHT_TO(String wEIGHT_TO) {
		WEIGHT_TO = wEIGHT_TO;
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
