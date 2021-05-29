package io.nzbee.view.product.tag.facet;

import java.util.Objects;

public class TagFacetView  {

	private String tagCode;

	private String tagDesc;
	
	private String locale;
	
	private int productCount;

	public TagFacetView() {
		
	}
	
	public String getTagCode() {
		return tagCode;
	}

	public void setTagCode(String tagCode) {
		this.tagCode = tagCode;
	}

	public String getTagDesc() {
		return tagDesc;
	}

	public void setTagDesc(String tagDesc) {
		this.tagDesc = tagDesc;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public int getProductCount() {
		return productCount;
	}

	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(this.getTagCode());
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
	
}
