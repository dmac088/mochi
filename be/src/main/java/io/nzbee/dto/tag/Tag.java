package io.nzbee.dto.tag;

import io.nzbee.dto.IDto;

public class Tag implements IDto {

	private Long tagId;
	
	private String tagCode;

	private String tagDesc;
	
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
	public String getType() {
		// TODO Auto-generated method stub
		return this.getClass().getSimpleName();
	}

}
