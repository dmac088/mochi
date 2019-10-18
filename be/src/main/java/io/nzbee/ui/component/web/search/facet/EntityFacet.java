package io.nzbee.ui.component.web.search.facet;

import io.nzbee.domain.IDomainObject;

public class EntityFacet implements IFacet {

	private final IDomainObject entity;

	public EntityFacet(IDomainObject entity) {
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
	public IDomainObject getEntity() {
		// TODO Auto-generated method stub
		return this.entity;
	}

	
}
