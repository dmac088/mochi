package io.nzbee.util.product;


public class ProductMasterSchema {

	private String PRODUCT_UPC_CODE;
	
	private String PRODUCT_STATUS_CODE;
	
	private String PRODUCT_CREATED_DATE;

	private String PRODUCT_DESCRIPTION_EN;
	
	private String PRODUCT_DESCRIPTION_HK;
	
	private String PRODUCT_LONG_DESCRIPTION_EN;
	
	private String PRODUCT_LONG_DESCRIPTION_HK;
	
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
	
	private String PRODUCT_TEMPLATE_CODE;
	
	private String PRODUCT_TEMPLATE_DESC_EN;
	
	private String PRODUCT_TEMPLATE_DESC_HK;
	
	private String TAG_CODE_A;
	
	private String TAG_CODE_B;
	
	private String TAG_CODE_C;
	
	private String TAG_CODE_D;
	
	private String TAG_CODE_E;
	

	public String get_PRODUCT_TEMPLATE_CODE() {
		return PRODUCT_TEMPLATE_CODE;
	}

	public void set_PRODUCT_TEMPLATE_CODE(String pRODUCT_TEMPLATE_CODE) {
		PRODUCT_TEMPLATE_CODE = pRODUCT_TEMPLATE_CODE;
	}

	public String get_PRODUCT_TEMPLATE_DESC_EN() {
		return PRODUCT_TEMPLATE_DESC_EN;
	}

	public void set_PRODUCT_TEMPLATE_DESC_EN(String pRODUCT_TEMPLATE_DESC_EN) {
		PRODUCT_TEMPLATE_DESC_EN = pRODUCT_TEMPLATE_DESC_EN;
	}

	public String get_PRODUCT_TEMPLATE_DESC_HK() {
		return PRODUCT_TEMPLATE_DESC_HK;
	}

	public void set_PRODUCT_TEMPLATE_DESC_HK(String pRODUCT_TEMPLATE_DESC_HK) {
		PRODUCT_TEMPLATE_DESC_HK = pRODUCT_TEMPLATE_DESC_HK;
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

	public String get_PRODUCT_LONG_DESCRIPTION_EN() {
		return this.PRODUCT_LONG_DESCRIPTION_EN;
	}

	public void set_PRODUCT_LONG_DESCRIPTION_EN(String pRODUCT_LONG_DESCRIPTION_EN) {
		this.PRODUCT_LONG_DESCRIPTION_EN = pRODUCT_LONG_DESCRIPTION_EN;
	}

	public String get_PRODUCT_LONG_DESCRIPTION_HK() {
		return this.PRODUCT_LONG_DESCRIPTION_HK;
	}

	public void set_PRODUCT_LONG_DESCRIPTION_HK(String pRODUCT_LONG_DESCRIPTION_HK) {
		this.PRODUCT_LONG_DESCRIPTION_HK = pRODUCT_LONG_DESCRIPTION_HK;
	}

	public double get_PRODUCT_RETAIL_PRICE_USD() {
		return this.PRODUCT_RETAIL_PRICE_USD;
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
	
	public String get_PRODUCT_STATUS_CODE() {
		return PRODUCT_STATUS_CODE;
	}

	public void set_PRODUCT_STATUS_CODE(String pRODUCT_STATUS_CODE) {
		PRODUCT_STATUS_CODE = pRODUCT_STATUS_CODE;
	}

	public String get_TAG_CODE_A() {
		return TAG_CODE_A;
	}

	public void set_TAG_CODE_A(String tAG_CODE_A) {
		TAG_CODE_A = tAG_CODE_A;
	}

	public String get_TAG_CODE_B() {
		return TAG_CODE_B;
	}

	public void set_TAG_CODE_B(String tAG_CODE_B) {
		TAG_CODE_B = tAG_CODE_B;
	}

	public String get_TAG_CODE_C() {
		return TAG_CODE_C;
	}

	public void set_TAG_CODE_C(String tAG_CODE_C) {
		TAG_CODE_C = tAG_CODE_C;
	}

	public String get_TAG_CODE_D() {
		return TAG_CODE_D;
	}

	public void set_TAG_CODE_D(String tAG_CODE_D) {
		TAG_CODE_D = tAG_CODE_D;
	}

	public String get_TAG_CODE_E() {
		return TAG_CODE_E;
	}

	public void set_TAG_CODE_E(String tAG_CODE_E) {
		TAG_CODE_E = tAG_CODE_E;
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
