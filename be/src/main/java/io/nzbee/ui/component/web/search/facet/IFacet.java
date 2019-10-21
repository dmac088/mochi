package io.nzbee.ui.component.web.search.facet;

import io.nzbee.domain.IDomainObject;

public interface IFacet {

	String getCode();
	
	String getDesc();
	
	boolean isHierarchical();

	IDomainObject getPayload();
}
