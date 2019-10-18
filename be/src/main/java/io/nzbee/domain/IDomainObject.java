package io.nzbee.domain;

import io.nzbee.ui.component.web.search.facet.SearchFacet;

public interface IDomainObject<T> {

	public String getCode();
	
	public String getDesc();
	
	public boolean isHierarchical();
	
}
