package io.nzbee.domain.tag;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.nzbee.domain.IDomainObject;

@JsonTypeName("tag")
public class Tag implements IDomainObject {

	private Long tagId;
	
	private String tagCode;

	private String tagDesc;
	
	private int objectCount;
	
	private String locale;
	
	private String currency;

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
	public String getCode() {
		// TODO Auto-generated method stub
		return this.tagCode;
	}

	@Override
	public boolean isHierarchical() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return objectCount;
	}

	@Override
	public String getDesc() {
		// TODO Auto-generated method stub
		return this.getTagDesc();
	}

	@Override
	public String getCurrency() {
		// TODO Auto-generated method stub
		return currency;
	}
	
	public void setCurrency(String currency) {
		this.currency = currency;
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
