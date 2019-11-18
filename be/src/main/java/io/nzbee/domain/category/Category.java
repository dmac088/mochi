package io.nzbee.domain.category;

import java.util.List;
import java.util.Objects;
import io.nzbee.domain.IHierarchicalDomainObject;
 
public abstract class Category implements IHierarchicalDomainObject {

	private String categoryCode;
	
	private String categoryDesc;
	
	private Long categoryLevel;
	
	private String categoryType;

	private Long childCategoryCount;

	private int objectCount;
	
	private String[] layoutCodes;
	
	private String locale;
	
	private String currency;

	public Category() {
		super();
	}
	
	public Category(
					String categoryCode, 
					String categoryDesc, 
					Long categoryLevel, 
					String categoryType,
					String lclCd, 
					String parentCode,
					int objectCount,
					List<String> layoutCodes) {
		super();
		this.categoryCode 	= categoryCode;
		this.categoryDesc 	= categoryDesc;
		this.categoryLevel 	= categoryLevel;
		this.categoryType 	= categoryType;
		this.objectCount	= objectCount;
	}

	protected String getCategoryCode() {
		return this.categoryCode;
	}

	protected void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
	
	protected String getCategoryDesc() {
		return this.categoryDesc;
	}

	protected void setCategoryDesc(String categoryDesc) {
		this.categoryDesc = categoryDesc;
	}

	protected Long getCategoryLevel() {
		return this.categoryLevel;
	}

	protected void setCategoryLevel(Long categoryLevel) {
		this.categoryLevel = categoryLevel;
	}

	public Long getChildCount() {
		return childCategoryCount;
	}

	public void setChildCount(Long childCategoryCount) {
		this.childCategoryCount = childCategoryCount;
	}
	
	public String getCategoryType() {
		return categoryType;
	}
	
	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}
	
	public int getCount() {
		return objectCount;
	}

	public void setCount(int objectCount) {
		this.objectCount = objectCount;
	}
	
	public String[] getLayoutCodes() {
		return layoutCodes;
	}

	public void setLayoutCodes(String[] layoutCodes) {
		this.layoutCodes = layoutCodes;
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
	public boolean equals(Object o) {
		 if (this == o) return true;
	     if (o == null || getClass() != o.getClass()) return false;
	     Category pcDto = (Category) o;
	     return this.categoryCode == pcDto.categoryCode;
	}

	@Override
	public int hashCode() {
		return Objects.hash(categoryCode);
	}

	@Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("domain.Category [")
        .append("categoryCode=").append(categoryCode)
        .append(", categoryDesc=").append(categoryDesc)
        .append(", categoryLevel=").append(categoryLevel)
        .append(", categoryType=").append(categoryType)
        .append(", childCategoryCount=").append(childCategoryCount)
        .append(", layoutCodes=").append(String.join(",", layoutCodes))
        .append("]");    	
        return builder.toString();
    }


}