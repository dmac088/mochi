package io.nzbee.view.category.product;

public class ProductCategoryView {

	private String categoryCode;
	
	private String categoryDesc;
	
	private String categoryType;
	
	private Long objectCount;
	
	private String locale;

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getCategoryDesc() {
		return categoryDesc;
	}

	public void setCategoryDesc(String categoryDesc) {
		this.categoryDesc = categoryDesc;
	}

	public String getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}

	public Long getObjectCount() {
		return objectCount;
	}

	public void setObjectCount(Long objectCount) {
		this.objectCount = objectCount;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}
	
}
