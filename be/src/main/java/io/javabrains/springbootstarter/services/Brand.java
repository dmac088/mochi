package io.javabrains.springbootstarter.services;

import java.util.Objects;

public class Brand {

	private Long brandId;
	
	private String brandCode;

	private String brandDesc;
	
	private Long productCount;
	
	private Long maxMarkDownPrice;

	public Long getBrandId() {
		return brandId;
	}

	public void setBrandId(Long brandId) {
		this.brandId = brandId;
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
	
	public Long getProductCount() {
		return productCount;
	}

	public void setProductCount(Long productCount) {
		this.productCount = productCount;
	}
	
	public Long getMaxMarkDownPrice() {
		return maxMarkDownPrice;
	}

	public void setMaxMarkDownPrice(Long maxMarkDownPrice) {
		this.maxMarkDownPrice = maxMarkDownPrice;
	}
	
	@Override
	public boolean equals(Object o) {
		 if (this == o) return true;
	     if (o == null || getClass() != o.getClass()) return false;
	     Brand pcDto = (Brand) o;
	     return this.brandId == pcDto.brandId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(brandId);
	}
	
}
