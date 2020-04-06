package io.nzbee.domain.category;

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
	
	public Category(
					String categoryCode, 
					String categoryDesc, 
					Long categoryLevel, 
					String categoryType,
					String lclCd, 
					String parentCode,
					int objectCount) {
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
	
	protected String getCategoryDesc() {
		return this.categoryDesc;
	}

	protected Long getCategoryLevel() {
		return this.categoryLevel;
	}

	public Long getChildCount() {
		return childCategoryCount;
	}
	
	public String getCategoryType() {
		return categoryType;
	}
	
	public int getCount() {
		return objectCount;
	}
	
	public String[] getLayoutCodes() {
		return layoutCodes;
	}
	
	public String getLocale() {
		return locale;
	}

	public String getCurrency() {
		return currency;
	}

	@Override
	public boolean equals(Object o) {
		 if (this == o) return true;
	     if (o == null || getClass() != o.getClass()) return false;
	     Category pcDto = (Category) o;
	     return this.getCode() == pcDto.getCode();
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.getCode());
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