package io.nzbee.domain;

import java.util.List;
import java.util.Objects;

import io.nzbee.entity.layout.Layout;

public class Category {
	
	private Long categoryId;
	
	private String categoryCode;
	
	private String categoryDesc;
	
	private Long categoryLevel;
	
	private String categoryType;

	private String lclCd;
	
	private List<Layout> layouts;
	
	private Long parentId;	

	private Long childCategoryCount;
//
//	private List<Category> children;
	
	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
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

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
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
	
	public List<Layout> getLayouts() {
		return layouts;
	}

	public void setLayouts(List<Layout> layouts) {
		this.layouts = layouts;
	}
	
//	public List<Category> getChildren() {
//		return children;
//	}
//
//	public void setChildren(List<Category> children) {
//		this.children = children;
//	}

	@Override
	public boolean equals(Object o) {
		 if (this == o) return true;
	     if (o == null || getClass() != o.getClass()) return false;
	     Category pcDto = (Category) o;
	     return this.categoryId == pcDto.categoryId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(categoryId);
	}

	@Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("domain.Category [")
        .append("categoryId=").append(categoryId)
        .append(", categoryCode=").append(categoryCode)
        .append(", categoryDesc=").append(categoryDesc)
        .append(", categoryLevel=").append(categoryLevel)
        .append(", categoryType=").append(categoryType)
        .append(", parentId=").append(parentId)
        .append(", childCategoryCount=").append(childCategoryCount)
        .append(", lclCd=").append(lclCd)
        .append("]");    	
        return builder.toString();
    }
}