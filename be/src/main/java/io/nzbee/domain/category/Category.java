package io.nzbee.domain.category;

import java.util.Objects;
import io.nzbee.domain.ILocalizedDomainObject;
 
public abstract class Category implements ILocalizedDomainObject {

	private String categoryCode;
	
	private String categoryDesc;
	
	private Long categoryLevel;

	protected String categoryType;

	private Long childCategoryCount;

	private int objectCount;
	
	private String locale;
	
	private String currency;

	public Category(String categoryCode, 
					String categoryDesc,
					Long categoryLevel,
					String lclCd, 
					String currency,
					int objectCount 
					) {
		super();
		this.categoryCode 	= categoryCode;
		this.categoryDesc 	= categoryDesc;
		this.categoryLevel 	= categoryLevel;
		this.objectCount	= objectCount;
		this.locale 		= lclCd;
		this.currency		= currency;
	}

	public String getCategoryCode() {
		return this.categoryCode;
	}
	
	public String getCategoryDesc() {
		return this.categoryDesc;
	}

	public Long getCategoryLevel() {
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
	     return this.getCategoryCode() == pcDto.getCategoryCode();
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.getCategoryCode());
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
        .append("]");    	
        return builder.toString();
    }


}