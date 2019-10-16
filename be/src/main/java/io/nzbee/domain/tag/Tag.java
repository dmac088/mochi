package io.nzbee.domain.tag;

import io.nzbee.domain.IDomainObject;
import io.nzbee.ui.component.web.facet.EntityFacet;

public class Tag implements IDomainObject{

	private Long tagId;
	
	private String tagCode;

	private String tagDesc;
	
	//private String tagType;
	
	private String locale;

	public Long getTagId() {
		return tagId;
	}

	public void setTagId(Long tagId) {
		this.tagId = tagId;
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
	
//	public String getTagType() {
//		return tagType;
//	}
//
//	public void setTagType(String tagType) {
//		this.tagType = tagType;
//	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}
	
	@Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("tag [Id=").append(tagId)
        		.append(", tagDesc=").append(tagDesc)
                .append(", locale=").append(locale);
        return builder.toString();
	}

	@Override
	public String getCode() {
		// TODO Auto-generated method stub
		return this.tagCode;
	}

	@Override
	public String getDesc() {
		// TODO Auto-generated method stub
		return this.tagDesc;
	}

	@Override
	public boolean isHierarchical() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public EntityFacet toFacet() {
		// TODO Auto-generated method stub
		return null;
	}

}
