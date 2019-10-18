package io.nzbee.ui.component.web.search.facet;

import io.nzbee.domain.IDomainObject;

public interface IFacet<T> {

	public String getCode();
	
	public String getDesc();
	
	public boolean isHierarchical();
	
	public IDomainObject<T> getEntity();
	
}
