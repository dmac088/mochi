package io.nzbee.dto.product;


//the DTO exposes the bits or the slice of the domain model that we want to expose to the 
//application UI, stitching the UI directly over the domain model results in verbosity in the 
//domain model in terms of Jackson annotations (i.e. Ignore that we typically want to avoid)

public class ProductDTOFull {

	private String productUPC;

	private String productDesc;
	
	private String productLongDesc;
	
	private Double productRetail;
	
	private Double productMarkdown;
	
	private String productType;

	private String primaryCategoryCode;

	private String primaryCategoryDesc;
	
	private String brandCode;
	
	private String brandDesc;
	
	private String locale;
	
	private String currency;
	
	public String getProductUPC() {
		return productUPC;
	}

	public void setProductUPC(String productUPC) {
		this.productUPC = productUPC;
	}

	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public String getProductLongDesc() {
		return productLongDesc;
	}

	public void setProductLongDesc(String productLongDesc) {
		this.productLongDesc = productLongDesc;
	}

	public Double getProductRetail() {
		return productRetail;
	}

	public void setProductRetail(Double productRetail) {
		this.productRetail = productRetail;
	}

	public Double getProductMarkdown() {
		return productMarkdown;
	}

	public void setProductMarkdown(Double productMarkdown) {
		this.productMarkdown = productMarkdown;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getPrimaryCategoryCode() {
		return primaryCategoryCode;
	}

	public void setPrimaryCategoryCode(String primaryCategoryCode) {
		this.primaryCategoryCode = primaryCategoryCode;
	}
	
	public String getPrimaryCategoryDesc() {
		return primaryCategoryDesc;
	}

	public void setPrimaryCategoryDesc(String primaryCategoryDesc) {
		this.primaryCategoryDesc = primaryCategoryDesc;
	}

	public String getBrandCode() {
		return brandCode;
	}

	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
	}

	public String getBrandDesc() {
		return brandDesc;
	}

	public void setBrandDesc(String brandDesc) {
		this.brandDesc = brandDesc;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}
}