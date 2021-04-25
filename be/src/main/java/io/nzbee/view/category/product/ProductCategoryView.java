package io.nzbee.view.category.product;

public class ProductCategoryView {

	private String categoryCode;
	
	private String categoryDesc;
	
	private String parentCode;
	
	private Long objectCount;
	
	private String locale;
	
	public ProductCategoryView(String categoryCode, String categoryDesc, String parentCode, Long objectCount, String locale) {
		super();
		this.categoryCode 	= categoryCode;
		this.categoryDesc 	= categoryDesc;
		this.parentCode 	= parentCode;
		this.objectCount 	= objectCount;
		this.locale 		= locale;
	}
	
	public ProductCategoryView() {
		super();
	}

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
	
	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
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
