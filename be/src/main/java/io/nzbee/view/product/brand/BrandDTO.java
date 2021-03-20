package io.nzbee.view.product.brand;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class BrandDTO {

	private String brandCode;
	
	private String brandDesc;
	
	@JsonIgnore
	private String locale;

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

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

}
