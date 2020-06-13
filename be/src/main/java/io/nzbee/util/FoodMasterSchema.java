package io.nzbee.util;


public class FoodMasterSchema {

	private String PRODUCT_UPC_CODE;
	
	private String PRODUCT_CREATED_DATE;

	private String PRODUCT_DESCRIPTION_EN;
	
	private String PRODUCT_DESCRIPTION_HK;
	
	private String BRAND_CODE;
	
	private String BRAND_DESCRIPTION_EN;
	
	private String BRAND_DESCRIPTION_HK;
	
	private double PRODUCT_RETAIL_PRICE_USD;
	
	private double PRODUCT_MARKDOWN_PRICE_USD;
	
	private double PRODUCT_RETAIL_PRICE_HKD;
	
	private double PRODUCT_MARKDOWN_PRICE_HKD;
	
	private String PRODUCT_IMAGE_EN;
	
	private String PRODUCT_IMAGE_HK;
	
	private String PRIMARY_CATEGORY_CODE;
	
	private String PRIMARY_CATEGORY_DESC_EN;
	
	private String PRIMARY_CATEGORY_DESC_HK;
	
	private String DEPARTMENT_CODE;
	
	private String DEPARTMENT_DESC_EN;
	
	private String DEPARTMENT_DESC_HK;
	
	private String EXPIRY_DATE;
	
	private String COUNTRY_OF_ORIGIN;

	public String get_EXPIRY_DATE() {
		return EXPIRY_DATE;
	}

	public void set_EXPIRY_DATE(String eXPIRY_DATE) {
		EXPIRY_DATE = eXPIRY_DATE;
	}

	public String get_COUNTRY_OF_ORIGIN() {
		return COUNTRY_OF_ORIGIN;
	}

	public void set_COUNTRY_OF_ORIGIN(String COUNTRY_OF_ORIGIN) {
		this.COUNTRY_OF_ORIGIN = COUNTRY_OF_ORIGIN;
	}

	public String get_DEPARTMENT_CODE() {
		return DEPARTMENT_CODE;
	}

	public void set_DEPARTMENT_CODE(String DEPARTMENT_CODE) {
		this.DEPARTMENT_CODE = DEPARTMENT_CODE;
	}

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

	public String get_PRIMARY_CATEGORY_CODE() {
		return PRIMARY_CATEGORY_CODE;
	}

	public void set_PRIMARY_CATEGORY_CODE(String PRIMARY_CATEGORY_CODE) {
		this.PRIMARY_CATEGORY_CODE = PRIMARY_CATEGORY_CODE;
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
	
	public String get_BRAND_CODE() {
		return BRAND_CODE;
	}

	public void set_BRAND_CODE(String BRAND_CODE) {
		this.BRAND_CODE = BRAND_CODE;
	}
	
	public String get_PRIMARY_CATEGORY_DESC_EN() {
		return PRIMARY_CATEGORY_DESC_EN;
	}

	public void set_PRIMARY_CATEGORY_DESC_EN(String pRIMARY_CATEGORY_DESC_EN) {
		PRIMARY_CATEGORY_DESC_EN = pRIMARY_CATEGORY_DESC_EN;
	}

	public String get_PRIMARY_CATEGORY_DESC_HK() {
		return PRIMARY_CATEGORY_DESC_HK;
	}

	public void set_PRIMARY_CATEGORY_DESC_HK(String pRIMARY_CATEGORY_DESC_HK) {
		PRIMARY_CATEGORY_DESC_HK = pRIMARY_CATEGORY_DESC_HK;
	}
	
	public String get_DEPARTMENT_DESC_EN() {
		return DEPARTMENT_DESC_EN;
	}

	public void set_DEPARTMENT_DESC_EN(String dEPARTMENT_DESC_EN) {
		DEPARTMENT_DESC_EN = dEPARTMENT_DESC_EN;
	}
	
	public String get_DEPARTMENT_DESC_HK() {
		return DEPARTMENT_DESC_HK;
	}

	public void set_DEPARTMENT_DESC_HK(String dEPARTMENT_DESC_HK) {
		DEPARTMENT_DESC_HK = dEPARTMENT_DESC_HK;
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
