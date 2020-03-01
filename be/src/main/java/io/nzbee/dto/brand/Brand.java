package io.nzbee.dto.brand;

import java.util.Objects;

import io.nzbee.domain.category.BrandCategory;
import io.nzbee.dto.IDto;

public class Brand implements IDto {

	private String brandCode;

	private String brandDesc;
	
	private String locale;
	
	private String currency;

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

	@Override
	public String getCode() {
		return this.getBrandCode();
	}

	@Override
	public String getType() {
		return this.getClass().getSimpleName();
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
