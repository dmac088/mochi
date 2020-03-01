package io.nzbee.dto.category;

import java.util.Objects;

import io.nzbee.dto.IDto;

public abstract class Category implements IDto {
	
	private Long categoryId;

	private String categoryCode;
	
	private String categoryDesc;
	
	private Long categoryLevel;

	private String locale;
	
	private String currency;

	private Long childCategoryCount;

	private int objectCount;
	
	private Double retailPrice;
	
	private Double markdownPrice;
	
	private String[] layoutCodes;

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
					String 	currency,
					int  	objectCount,
					Double  retailPrice,
					Double  markdownPrice
					) {
		super();
		this.categoryId 	= categoryid;
		this.categoryCode 	= categoryCode;
		this.categoryDesc 	= categoryDesc;
		this.categoryLevel 	= categoryLevel;
		this.locale 		= locale;
		this.currency 		= currency;
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

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public Long getChildCategoryCount() {
		return childCategoryCount;
	}

	public void setChildCategoryCount(Long childCategoryCount) {
		this.childCategoryCount = childCategoryCount;
	}
	
	public int getObjectCount() {
		return objectCount;
	}

	public void setObjectCount(int objectCount) {
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
	
	public String[] getLayoutCodes() {
		return layoutCodes;
	}

	public void setLayoutCodes(String[] layoutCodes) {
		this.layoutCodes = layoutCodes;
	}
	
	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	@Override
	public String getType() {
		return this.getClass().getSimpleName();
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
        .append(", childCategoryCount=").append(childCategoryCount)
        .append(", locale=").append(locale)
        .append(", currency=").append(currency)
        .append("]");    	
        return builder.toString();
    }

	@Override
	public String getCode() {
		// TODO Auto-generated method stub
		return this.getCategoryCode();
	}


}