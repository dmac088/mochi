package io.nzbee.domain.category;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.nzbee.domain.ILocalizedDomainObject;

@JsonTypeInfo(
	    use = JsonTypeInfo.Id.NAME,
	    include = JsonTypeInfo.As.PROPERTY,
	    property="type")
@JsonSubTypes( {@JsonSubTypes.Type(value = ProductCategory.class, 	name = "productcategory"),
				@JsonSubTypes.Type(value = BrandCategory.class, 	name = "brandcategory")})
public abstract class Category implements ILocalizedDomainObject {

	protected String categoryCode;
	
	protected String categoryDesc;
	
	protected String categoryType;
	
	protected Long objectCount;
	
	protected String locale;
	

	public Category(		String categoryCode, 
							String categoryDesc,
							String lclCd, 
							Long objectCount 
					) {
		super();
		this.categoryCode 	= categoryCode;
		this.categoryDesc 	= categoryDesc;
		this.objectCount	= objectCount;
		this.locale 		= lclCd;
	}
	
	public Category(		String 	categoryCode, 
							String categoryDesc,
							String lclCd
					) {
		super();
		this.categoryCode 	= categoryCode;
		this.categoryDesc 	= categoryDesc;
		this.locale 		= lclCd;
	}

	public String getCategoryCode() {
		return this.categoryCode;
	}
	
	public String getCategoryDesc() {
		return this.categoryDesc;
	}
	
	public String getCategoryType() {
		return categoryType;
	}
	
	public Long getCount() {
		return objectCount;
	}
	
	public String getLocale() {
		return locale;
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
        .append(", categoryType=").append(categoryType)
        .append("]");    	
        return builder.toString();
    }


}