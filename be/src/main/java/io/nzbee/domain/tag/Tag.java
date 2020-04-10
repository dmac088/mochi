package io.nzbee.domain.tag;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.nzbee.domain.IDomainObject;
import io.nzbee.domain.product.Product;

@JsonTypeName("tag")
public class Tag implements IDomainObject {

	private String tagCode;

	private String tagDesc;
	
	private int objectCount;
	
	private String locale;
	
	private String currency;
	
	private List<Product> products;

	public Tag(	String tagCode,
				String tagDesc,
				String locale,
				String currency) {
		this.tagCode = tagCode;
		this.tagDesc = tagDesc;
		this.locale = locale;
		this.currency = currency;
		this.products = new ArrayList<Product>();
	}

	public String getTagCode() {
		return tagCode;
	}

	public String getTagDesc() {
		return tagDesc;
	}

	public String getLocale() {
		return locale;
	}
	
	@Override
	public String getCode() {
		return this.tagCode;
	}

	@Override
	public boolean isHierarchical() {
		return false;
	}

	@Override
	public int getCount() {
		return objectCount;
	}

	@Override
	public String getDesc() {
		return this.getTagDesc();
	}

	@Override
	public String getCurrency() {
		return currency;
	}
	
	public List<Product> getProducts() {
		return products;
	}
	
	@Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("tag [code=").append(tagCode)
        		.append(", tagDesc=").append(tagDesc)
                .append(", locale=").append(locale);
        return builder.toString();
	}
	
	@Override
	public boolean equals(Object o) {
		 if (this == o) return true;
	     if (o == null || getClass() != o.getClass()) return false;
	     Tag pcDto = (Tag) o;
	     return this.getCode() == pcDto.getCode();
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.getCode());
	}
	
}
