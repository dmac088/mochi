package io.nzbee.entity;

public class ProductStatus {
	
	private Long productStatusId;

	private String productStatusCode;
	
	private String productStatusDesc;

	public Long getProductStatusId() {
		return productStatusId;
	}

	public void setProductStatusId(Long productStatusId) {
		this.productStatusId = productStatusId;
	}

	public String getProductStatusCode() {
		return productStatusCode;
	}

	public void setProductStatusCode(String productStatusCode) {
		this.productStatusCode = productStatusCode;
	}

	public String getProductStatusDesc() {
		return productStatusDesc;
	}

	public void setProductStatusDesc(String productStatusDesc) {
		this.productStatusDesc = productStatusDesc;
	}
}
