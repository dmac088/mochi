package io.nzbee.domain;

import io.nzbee.ui.component.web.facet.EntityFacet;

public interface IDomainObject<T> {

	public String getCode();
	
	public String getDesc();
	
	public boolean isHierarchical();
	
	public EntityFacet<T> toFacet();
	
}
