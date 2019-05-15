package io.nzbee.entity;

public class ProductTag {
	
	private Long productTagId;
	
	private String productTagDesc;

	public Long getTagId() {
		return productTagId;
	}

	public void setTagId(Long productTagId) {
		this.productTagId = productTagId;
	}

	public String getTagDesc() {
		return productTagDesc;
	}

	public void setTagDesc(String productTagDesc) {
		this.productTagDesc = productTagDesc;
	}
}
