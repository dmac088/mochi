package io.nzbee.dto;

import java.util.Date;

public class InboundProductMaster {

	private Long productId;

	private String productUPC;
	
	private Date productCreateDt;

	private String productDesc;
	
	private String brandDesc;
	
	private double productRetail;
	
	private double productMarkdown;
	
	private String productImage;
	
	private String lclCd;
	
	private String primaryCategoryPath; 


	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

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

	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public double getProductRetail() {
		return productRetail;
	}

	public void setProductRetail(double productRetail) {
		this.productRetail = productRetail;
	}

	public double getProductMarkdown() {
		return productMarkdown;
	}

	public void setProductMarkdown(double productMarkdown) {
		this.productMarkdown = productMarkdown;
	}

	public String getProductImage() {
		return productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}

	public String getLclCd() {
		return lclCd;
	}

	public void setLclCd(String lclCd) {
		this.lclCd = lclCd;
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
	
}
