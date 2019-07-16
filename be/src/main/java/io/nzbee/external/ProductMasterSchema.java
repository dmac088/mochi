package io.nzbee.external;

import java.util.Date;

public class ProductMasterSchema {

	private String productUPC;
	
	private Date productCreateDt;

	private String productDescEN;
	
	private String productDescHK;
	
	private String brandDesc;
	
	private double productRetailUSD;
	
	private double productMarkdownUSD;
	
	private double productRetailHKD;
	
	private double productMarkdownHKD;
	
	private String primaryCategoryPath; 

	public String getProductUPC() {
		return productUPC;
	}

	public void setProductUPC(String productUPC) {
		this.productUPC = productUPC;
	}

	public Date getProductCreateDt() {
		return productCreateDt;
	}

	public void setProductCreateDt(Date productCreateDt) {
		this.productCreateDt = productCreateDt;
	}
	
	public String getBrandDesc() {
		return brandDesc;
	}

	public void setBrandDesc(String brandDesc) {
		this.brandDesc = brandDesc;
	}
	
	public String getPrimaryCategoryPath() {
		return primaryCategoryPath;
	}

	public void setPrimaryCategoryPath(String primaryCategoryPath) {
		this.primaryCategoryPath = primaryCategoryPath;
	}

	public String getProductDescEN() {
		return productDescEN;
	}

	public void setProductDescEN(String productDescEN) {
		this.productDescEN = productDescEN;
	}

	public String getProductDescHK() {
		return productDescHK;
	}

	public void setProductDescHK(String productDescHK) {
		this.productDescHK = productDescHK;
	}

	public double getProductRetailUSD() {
		return productRetailUSD;
	}

	public void setProductRetailUSD(double productRetailUSD) {
		this.productRetailUSD = productRetailUSD;
	}

	public double getProductMarkdownUSD() {
		return productMarkdownUSD;
	}

	public void setProductMarkdownUSD(double productMarkdownUSD) {
		this.productMarkdownUSD = productMarkdownUSD;
	}

	public double getProductRetailHKD() {
		return productRetailHKD;
	}

	public void setProductRetailHKD(double productRetailHKD) {
		this.productRetailHKD = productRetailHKD;
	}

	public double getProductMarkdownHKD() {
		return productMarkdownHKD;
	}

	public void setProductMarkdownHKD(double productMarkdownHKD) {
		this.productMarkdownHKD = productMarkdownHKD;
	}
	
}
