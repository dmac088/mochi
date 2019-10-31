package io.nzbee.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(
	    use = JsonTypeInfo.Id.CLASS,
	    include = JsonTypeInfo.As.PROPERTY,
	    property = "@class")
public interface IDomainObject {

	public String getCode();
	
	public String getDesc();
	
	public int getCount();
	
	public boolean isHierarchical();
	
	public IService getServiceBean();
}
