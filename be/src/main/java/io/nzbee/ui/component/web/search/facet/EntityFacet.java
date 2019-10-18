package io.nzbee.ui.component.web.search.facet;

import io.nzbee.domain.IDomainObject;

public class EntityFacet<T> implements IFacet<T> {

	private final IDomainObject<T> entity;

	public EntityFacet(IDomainObject<T> entity) {
	  this.entity = entity;
	}
	
	@Override
	public String getCode() {
		// TODO Auto-generated method stub
		return this.entity.getCode();
	}

	@Override
	public String getDesc() {
		// TODO Auto-generated method stub
		return this.entity.getDesc();
	}

	@Override
	public boolean isHierarchical() {
		// TODO Auto-generated method stub
		return this.entity.isHierarchical();
	}

	@Override
	public IDomainObject<T> getEntity() {
		// TODO Auto-generated method stub
		return this.entity;
	}

	
}
