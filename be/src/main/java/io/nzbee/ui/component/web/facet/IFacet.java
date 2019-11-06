package io.nzbee.ui.component.web.facet;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(
	    use = JsonTypeInfo.Id.CLASS,
	    include = JsonTypeInfo.As.PROPERTY,
	    property = "@class")
public interface IFacet<T> {

	String getId();
	
	String getDisplayValue();
	
	boolean isHierarchical();

	T getPayload();
	
	String getType();
	
	String getPayloadType();

	String getValue();
	
}
