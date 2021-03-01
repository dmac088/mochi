package io.nzbee.util.product.physical;

import io.nzbee.util.product.ProductMasterSchema;

public class PhysicalProductMasterSchema extends ProductMasterSchema {

	
	private String WIDTH;
	
	private String HEIGHT;
	
	private String LENGTH;
	
	private String WEIGHT;
	
	public String get_WIDTH() {
		return WIDTH;
	}

	public void set_WIDTH(String wIDTH) {
		WIDTH = wIDTH;
	}

	public String get_HEIGHT() {
		return HEIGHT;
	}

	public void set_HEIGHT(String hEIGHT) {
		HEIGHT = hEIGHT;
	}

	public String get_LENGTH() {
		return LENGTH;
	}

	public void set_LENGTH(String lENGTH) {
		LENGTH = lENGTH;
	}

	public String get_WEIGHT() {
		return WEIGHT;
	}

	public void set_WEIGHT(String wEIGHT) {
		WEIGHT = wEIGHT;
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
