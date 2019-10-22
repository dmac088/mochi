package io.nzbee.ui.component.web.search.facet;

import io.nzbee.domain.IDomainObject;

public interface IFacet {

	String getId();
	
	String getDisplayValue();
	
	boolean isHierarchical();

	IDomainObject getPayload();
	
	String getType();
	
	String getPayloadType();
	
}
