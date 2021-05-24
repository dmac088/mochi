package io.nzbee.view.product.tag.facet;

import java.util.Objects;
import io.nzbee.search.facet.IFacet;

public class TagFacetView implements IFacet  {

	private String tagCode;

	private String tagDesc;
	
	private String locale;
	
	private int productCount;

	public TagFacetView(String tagCode,
						String tagDesc,
						int productCount,
						String locale) {
		this.tagCode = tagCode;
		this.tagDesc = tagDesc;
		this.productCount = productCount;
		this.locale = locale;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(this.getId());
	}

	@Override
	public String getId() {
		return this.tagCode;
	}

	@Override
	public String getDesc() {
		return this.tagDesc;
	}

	@Override
	public Boolean isHierarchical() {
		return false;
	}

	@Override
	public String getType() {
		return "SimpleFacet";
	}

	@Override
	public String getObjectType() {
		return null;
	}

	@Override
	public String getFacetingName() {
		return "tag";
	}

	@Override
	public int getCount() {
		return productCount;
	}

	@Override
	public String getValue() {
		return this.getId();
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
	     return this.getId() == pcDto.getId();
	}
	
}
