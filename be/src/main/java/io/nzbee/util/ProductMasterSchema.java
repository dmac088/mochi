package io.nzbee.util;


public class ProductMasterSchema {

	private String PRODUCT_UPC_CODE;
	
	private String PRODUCT_CREATED_DATE;

	private String PRODUCT_DESCRIPTION_EN;
	
	private String PRODUCT_DESCRIPTION_HK;
	
	private String BRAND_DESCRIPTION_EN;
	
	private String BRAND_DESCRIPTION_HK;
	
	private double PRODUCT_RETAIL_PRICE_USD;
	
	private double PRODUCT_MARKDOWN_PRICE_USD;
	
	private double PRODUCT_RETAIL_PRICE_HKD;
	
	private double PRODUCT_MARKDOWN_PRICE_HKD;
	
	private String PRODUCT_IMAGE_EN;
	
	private String PRODUCT_IMAGE_HK;
	
	private String PRIMARY_CATEGORY_PATH; 

	public String get_PRODUCT_UPC_CODE() {
		return PRODUCT_UPC_CODE;
	}

	public void set_PRODUCT_UPC_CODE(String PRODUCTUPC) {
		this.PRODUCT_UPC_CODE = PRODUCTUPC;
	}

	public String get_PRODUCT_CREATED_DATE() {
		return PRODUCT_CREATED_DATE;
	}

	public void set_PRODUCT_CREATED_DATE(String PRODUCTCreateDt) {
		this.PRODUCT_CREATED_DATE = PRODUCTCreateDt;
	}

	public String get_PRODUCT_DESCRIPTION_EN() {
		return PRODUCT_DESCRIPTION_EN;
	}

	public void set_PRODUCT_DESCRIPTION_EN(String PRODUCT_DESCRIPTION_EN) {
		this.PRODUCT_DESCRIPTION_EN = PRODUCT_DESCRIPTION_EN;
	}

	public String get_PRODUCT_DESCRIPTION_HK() {
		return PRODUCT_DESCRIPTION_HK;
	}

	public void set_PRODUCT_DESCRIPTION_HK(String PRODUCT_DESCRIPTION_HK) {
		this.PRODUCT_DESCRIPTION_HK = PRODUCT_DESCRIPTION_HK;
	}

	public String get_BRAND_DESCRIPTION_EN() {
		return BRAND_DESCRIPTION_EN;
	}

	public void set_BRAND_DESCRIPTION_EN(String BRAND_DESCRIPTION_EN) {
		this.BRAND_DESCRIPTION_EN = BRAND_DESCRIPTION_EN;
	}

	public String get_BRAND_DESCRIPTION_HK() {
		return BRAND_DESCRIPTION_HK;
	}

	public void set_BRAND_DESCRIPTION_HK(String BRAND_DESCRIPTION_HK) {
		this.BRAND_DESCRIPTION_HK = BRAND_DESCRIPTION_HK;
	}

	public double get_PRODUCT_RETAIL_PRICE_USD() {
		return PRODUCT_RETAIL_PRICE_USD;
	}

	public void set_PRODUCT_RETAIL_PRICE_USD(double PRODUCT_RETAIL_PRICE_USD) {
		this.PRODUCT_RETAIL_PRICE_USD = PRODUCT_RETAIL_PRICE_USD;
	}

	public double get_PRODUCT_MARKDOWN_PRICE_USD() {
		return PRODUCT_MARKDOWN_PRICE_USD;
	}

	public void set_PRODUCT_MARKDOWN_PRICE_USD(double PRODUCT_MARKDOWN_PRICE_USD) {
		this.PRODUCT_MARKDOWN_PRICE_USD = PRODUCT_MARKDOWN_PRICE_USD;
	}

	public double get_PRODUCT_RETAIL_PRICE_HKD() {
		return PRODUCT_RETAIL_PRICE_HKD;
	}

	public void set_PRODUCT_RETAIL_PRICE_HKD(double PRODUCT_RETAIL_PRICE_HKD) {
		this.PRODUCT_RETAIL_PRICE_HKD = PRODUCT_RETAIL_PRICE_HKD;
	}

	public double get_PRODUCT_MARKDOWN_PRICE_HKD() {
		return PRODUCT_MARKDOWN_PRICE_HKD;
	}

	public void set_PRODUCT_MARKDOWN_PRICE_HKD(double PRODUCT_MARKDOWN_PRICE_HKD) {
		this.PRODUCT_MARKDOWN_PRICE_HKD = PRODUCT_MARKDOWN_PRICE_HKD;
	}

	public String get_PRIMARY_CATEGORY_PATH() {
		return PRIMARY_CATEGORY_PATH;
	}

	public void set_PRIMARY_CATEGORY_PATH(String PRIMARY_CATEGORY_PATH) {
		this.PRIMARY_CATEGORY_PATH = PRIMARY_CATEGORY_PATH;
	}

	public String get_PRODUCT_IMAGE_EN() {
		return PRODUCT_IMAGE_EN;
	}

	public void set_PRODUCT_IMAGE_EN(String PRODUCT_IMAGE_EN) {
		this.PRODUCT_IMAGE_EN = PRODUCT_IMAGE_EN;
	}

	public String get_PRODUCT_IMAGE_HK() {
		return PRODUCT_IMAGE_HK;
	}

	public void set_PRODUCT_IMAGE_HK(String PRODUCT_IMAGE_HK) {
		this.PRODUCT_IMAGE_HK = PRODUCT_IMAGE_HK;
	}
	
	@Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("ProductMasterSchema [PRODUCT_UPC_CODE=").append(PRODUCT_UPC_CODE)
        	   .append(", PRODUCT_CREATED_DATE=").append(PRODUCT_CREATED_DATE)
        	   .append(", PRODUCT_DESCRIPTION_EN=").append(PRODUCT_DESCRIPTION_EN)
        	   .append(", PRODUCT_DESCRIPTION_HK=").append(PRODUCT_DESCRIPTION_HK)
        	   .append(", BRAND_DESCRIPTION_EN=").append(BRAND_DESCRIPTION_EN)
        	   .append(", BRAND_DESCRIPTION_HK=").append(BRAND_DESCRIPTION_HK)
        	   .append(", PRODUCT_RETAIL_PRICE_USD=").append(PRODUCT_RETAIL_PRICE_USD)
        	   .append(", PRODUCT_MARKDOWN_PRICE_USD=").append(PRODUCT_MARKDOWN_PRICE_USD)
        	   .append(", PRODUCT_RETAIL_PRICE_HKD=").append(PRODUCT_RETAIL_PRICE_HKD)
        	   .append(", PRODUCT_MARKDOWN_PRICE_HKD=").append(PRODUCT_MARKDOWN_PRICE_HKD)
        	   .append(", PRODUCT_IMAGE_EN=").append(PRODUCT_IMAGE_EN)
        	   .append(", PRODUCT_IMAGE_HK=").append(PRODUCT_IMAGE_HK)
        	   .append(", PRIMARY_CATEGORY_PATH=").append(PRIMARY_CATEGORY_PATH)
               .append("]");
        return builder.toString();
        
    }
}
