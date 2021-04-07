package io.nzbee.view.product.physical;


//the DTO exposes the bits or the slice of the domain model that we want to expose to the 
//application UI, stitching the UI directly over the domain model results in verbosity in the 
//domain model in terms of Jackson annotations (i.e. Ignore that we typically want to avoid)

public class PhysicalProductLightView {

	private String productUPC;

	private String productDesc;

	private Double productRetail;
	
	private Double productMarkdown;
	
	private String brandDesc;
	
	private boolean inStock;
	
	private String productImage;

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
