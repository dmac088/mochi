package io.nzbee.view.product.brand;

public class BrandView {

	private String brandCode;

	private String brandDesc;
	
	private String locale;

	public BrandView(String brandCode, String brandDesc, String locale) {
		this.brandCode = brandCode;
		this.brandDesc = brandDesc;
		this.locale    = locale;
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

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

}


