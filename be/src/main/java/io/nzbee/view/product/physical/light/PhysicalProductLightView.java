package io.nzbee.view.product.physical.light;

import java.math.BigDecimal;

public class PhysicalProductLightView {

	private String productUPC;

	private String productDesc;
	
	private BigDecimal productRetail;
	
	private BigDecimal productMarkdown;
	
	private String productType;
	
	private String brandDesc;
	
	private boolean inStock;
	
	private String productImage;

	public PhysicalProductLightView(String productUPC, String productDesc, BigDecimal productRetail,
			BigDecimal productMarkdown, String productType, String brandDesc, boolean inStock, String productImage) {
		super();
		this.productUPC = productUPC;
		this.productDesc = productDesc;
		this.productRetail = productRetail;
		this.productMarkdown = productMarkdown;
		this.productType = productType;
		this.brandDesc = brandDesc;
		this.inStock = inStock;
		this.productImage = productImage;
	}
	
	public PhysicalProductLightView() {
		super();
	}

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

	public BigDecimal getProductRetail() {
		return productRetail;
	}

	public void setProductRetail(BigDecimal productRetail) {
		this.productRetail = productRetail;
	}

	public BigDecimal getProductMarkdown() {
		return productMarkdown;
	}

	public void setProductMarkdown(BigDecimal productMarkdown) {
		this.productMarkdown = productMarkdown;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getBrandDesc() {
		return brandDesc;
	}

	public void setBrandDesc(String brandDesc) {
		this.brandDesc = brandDesc;
	}

	public boolean isInStock() {
		return inStock;
	}

	public void setInStock(boolean inStock) {
		this.inStock = inStock;
	}

	public String getProductImage() {
		return productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}

	
}
