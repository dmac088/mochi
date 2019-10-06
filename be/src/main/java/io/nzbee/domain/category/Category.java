package io.nzbee.domain.category;

import java.util.Objects;

public abstract class Category {

	private String categoryCode;
	
	private String categoryDesc;
	
	private Long categoryLevel;
	
	private String categoryType;
	
	private String parentCode;	

	private Long childCategoryCount;

	private Long objectCount;
	
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
					Long objectCount) {
		super();
		this.categoryCode 	= categoryCode;
		this.categoryDesc 	= categoryDesc;
		this.categoryLevel 	= categoryLevel;
		this.categoryType 	= categoryType;
		this.parentCode 	= parentCode;
		this.objectCount	= objectCount;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public String getCategoryDesc() {
		return categoryDesc;
	}

	public void setCategoryDesc(String categoryDesc) {
		this.categoryDesc = categoryDesc;
	}
	
	public Long getCategoryLevel() {
		return categoryLevel;
	}

	public void setCategoryLevel(Long categoryLevel) {
		this.categoryLevel = categoryLevel;
	}

	public Long getChildCategoryCount() {
		return childCategoryCount;
	}

	public void setChildCategoryCount(Long childCategoryCount) {
		this.childCategoryCount = childCategoryCount;
	}
	
	public String getCategoryType() {
		return categoryType;
	}
	
	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}
	
	public Long getCount() {
		return objectCount;
	}

	public void setCount(Long objectCount) {
		this.objectCount = objectCount;
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
        .append(", parentCode=").append(parentCode)
        .append(", childCategoryCount=").append(childCategoryCount)
        .append("]");    	
        return builder.toString();
    }


}