package io.nzbee.domain;

import java.util.Objects;

public class Brand {

	private String brandCode;

	private String brandDesc;
	
	private BrandCategory category;
	
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
	
	public BrandCategory getCategory() {
		return category;
	}

	public void setCategory(BrandCategory category) {
		this.category = category;
	}
	
	@Override
	public boolean equals(Object o) {
		 if (this == o) return true;
	     if (o == null || getClass() != o.getClass()) return false;
	     Brand pcDto = (Brand) o;
	     return this.brandCode == pcDto.brandCode;
	}

	@Override
	public int hashCode() {
		return Objects.hash(brandCode);
	}
	
}
