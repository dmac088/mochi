package io.nzbee.dto.category;

import java.util.Objects;

public abstract class Category {
	
	private Long id;

	private String categoryCode;
	
	private String categoryDesc;
	
	private Long categoryLevel;
	
	private String categoryType;

	private String lclCd;
	
	private String parentCode;	

	private Long childCategoryCount;
	
	public abstract Long getCount();
	
	public abstract void setCount(Long count);
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public Category(Long id, 
					String categoryCode, 
					String categoryDesc, 
					Long categoryLevel, 
					String categoryType,
					String lclCd, 
					String parentCode) {
		this.id = id;
		this.categoryCode = categoryCode;
		this.categoryDesc = categoryDesc;
		this.categoryLevel = categoryLevel;
		this.categoryType = categoryType;
		this.lclCd = lclCd;
		this.parentCode = parentCode;
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

	public String getLclCd() {
		return lclCd;
	}

	public void setLclCd(String lclCd) {
		this.lclCd = lclCd;
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
        .append(", lclCd=").append(lclCd)
        .append("]");    	
        return builder.toString();
    }


}