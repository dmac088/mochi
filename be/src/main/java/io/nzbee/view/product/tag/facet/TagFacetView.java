package io.nzbee.view.product.tag.facet;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import io.nzbee.domain.ILocalizedDomainObject;
import io.nzbee.domain.product.Product;

//Anemic
public class TagFacetView implements ILocalizedDomainObject {

	private String tagCode;

	private String tagDesc;
	
	private String locale;
	
	private Long objectCount;

	private List<Product> products;

	public TagFacetView(	String tagCode,
				String tagDesc,
				Long objectCount,
				String locale) {
		this.tagCode = tagCode;
		this.tagDesc = tagDesc;
		this.objectCount = objectCount;
		this.locale = locale;
		this.products = new ArrayList<Product>();
	}
	
	public TagFacetView(	String tagCode,
			String tagDesc,
			String locale) {
	this.tagCode = tagCode;
	this.tagDesc = tagDesc;
	this.locale = locale;
	this.products = new ArrayList<Product>();
	}
	
	public Long getCount() {
		return objectCount;
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
	     TagFacetView pcDto = (TagFacetView) o;
	     return this.getTagCode() == pcDto.getTagCode();
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.getTagCode());
	}
	
}
