package io.nzbee.dto.category;

import java.util.List;
import java.util.Objects;

public class Category {
	
	private Long categoryId;

	private String categoryCode;
	
	private String categoryDesc;
	
	private Long categoryLevel;
	
	private String categoryType;

	private String lclCd;
	
	private String parentCode;	

	private Long childCategoryCount;

	private Long objectCount;
	
	private Double retailPrice;
	
	private Double markdownPrice;
	
	List<String> layoutCodes;

	public long getCategoryId() {
		return categoryId;
	}

	public void setId(long id) {
		this.categoryId = id;
	}
	
	public Category() {
		super();
	}
	
	public Category(Long 	categoryid, 
					String 	categoryCode,
					Long 	categoryLevel,
					String 	parentCategoryCode,
					String 	categoryDesc, 
					String  categoryImagePath,
					String  categoryTypeCode,
					String  locale,
					Long  	objectCount,
					Double  retailPrice,
					Double  markdownPrice
					) {
		super();
		this.categoryId 	= categoryid;
		this.categoryCode 	= categoryCode;
		this.categoryDesc 	= categoryDesc;
		this.categoryLevel 	= categoryLevel;
		this.categoryType 	= categoryTypeCode;
		this.lclCd 			= locale;
		this.parentCode 	= parentCategoryCode;
		this.objectCount	= objectCount;
		this.retailPrice	= retailPrice;
		this.markdownPrice	= markdownPrice;
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
	
	public Long getObjectCount() {
		return objectCount;
	}

	public void setObjectCount(Long objectCount) {
		this.objectCount = objectCount;
	}
	
	public Double getRetailPrice() {
		return retailPrice;
	}

	public void setRetailPrice(Double retailPrice) {
		this.retailPrice = retailPrice;
	}

	public Double getMarkdownPrice() {
		return markdownPrice;
	}

	public void setMarkdownPrice(Double markdownPrice) {
		this.markdownPrice = markdownPrice;
	}
	
	public List<String> getLayoutCodes() {
		return layoutCodes;
	}

	public void setLayoutCodes(List<String> layoutCodes) {
		this.layoutCodes = layoutCodes;
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