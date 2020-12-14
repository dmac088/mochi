package io.nzbee.util.product.shipping;

import io.nzbee.util.product.ProductMasterSchema;

public class ShippingProductMasterSchema extends ProductMasterSchema {

	private String COUNTRY_CODE;
	
	private String COUNTRY_NAME;
	
	private String CITY_NAME;
	
	private String POST_CODE;
	
	
	
	public String get_COUNTRY_CODE() {
		return COUNTRY_CODE;
	}



	public void set_COUNTRY_CODE(String cOUNTRY_CODE) {
		COUNTRY_CODE = cOUNTRY_CODE;
	}



	public String get_COUNTRY_NAME() {
		return COUNTRY_NAME;
	}



	public void set_COUNTRY_NAME(String cOUNTRY_NAME) {
		COUNTRY_NAME = cOUNTRY_NAME;
	}



	public String get_CITY_NAME() {
		return CITY_NAME;
	}



	public void set_CITY_NAME(String cITY_NAME) {
		CITY_NAME = cITY_NAME;
	}



	public String get_POST_CODE() {
		return POST_CODE;
	}



	public void set_POST_CODE(String pOST_CODE) {
		POST_CODE = pOST_CODE;
	}



	@Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("ProductMasterSchema [PRODUCT_UPC_CODE=").append(PRODUCT_UPC_CODE)
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
               .append("]");
        return builder.toString();
        
    }
}
