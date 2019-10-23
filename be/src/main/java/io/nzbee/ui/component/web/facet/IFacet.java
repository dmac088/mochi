package io.nzbee.ui.component.web.facet;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.nzbee.domain.IDomainObject;

public interface IFacet {

	String getId();
	
	String getDisplayValue();
	
	boolean isHierarchical();

	@JsonIgnore
	IDomainObject getPayload();
	
	String getType();
	
	String getPayloadType();
	
}
