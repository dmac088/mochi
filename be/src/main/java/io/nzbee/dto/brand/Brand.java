package io.nzbee.dto.brand;

import java.util.Objects;

import io.nzbee.domain.category.BrandCategory;
import io.nzbee.dto.IDto;

public class Brand implements IDto {

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

	@Override
	public String getCode() {
		// TODO Auto-generated method stub
		return this.getBrandCode();
	}

	@Override
	public String getDesc() {
		// TODO Auto-generated method stub
		return this.getBrandDesc();
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return this.getClass().getSimpleName();
	}
	
}
