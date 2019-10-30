package io.nzbee.ui.component.web.facet;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.nzbee.domain.IDomainObject;


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(
	    use = JsonTypeInfo.Id.CLASS,
	    include = JsonTypeInfo.As.PROPERTY,
	    property = "@class")
public abstract class Facet {

	public abstract String getId();
	
	public abstract String getDisplayValue();
	
	public abstract boolean isHierarchical();

	public abstract IDomainObject getPayload();
	
	public abstract String getType();
	
	public abstract String getPayloadType();

	public abstract String getValue();
	
}
