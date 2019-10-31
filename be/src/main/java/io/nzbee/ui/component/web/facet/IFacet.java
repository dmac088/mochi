package io.nzbee.ui.component.web.facet;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.nzbee.domain.IDomainObject;
import io.nzbee.domain.IService;


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(
	    use = JsonTypeInfo.Id.CLASS,
	    include = JsonTypeInfo.As.PROPERTY,
	    property = "@class")
public interface IFacet {

	String getId();
	
	String getDisplayValue();
	
	boolean isHierarchical();

	IDomainObject getPayload();
	
	IService getPayloadServiceBean();
	
	String getType();
	
	String getPayloadType();

	String getValue();
	
}
