package io.nzbee.ui.component.web.search.facet;

import io.nzbee.domain.IDomainObject;

public class EntityFacet implements IFacet {

	private final IDomainObject payload;

	public EntityFacet(IDomainObject entity) {
	  this.payload = entity;
	}
	
	@Override
	public String getCode() {
		// TODO Auto-generated method stub
		return this.payload.getCode();
	}

	@Override
	public String getDesc() {
		// TODO Auto-generated method stub
		return this.payload.getDesc();
	}

	@Override
	public boolean isHierarchical() {
		// TODO Auto-generated method stub
		return this.payload.isHierarchical();
	}

	@Override
	public IDomainObject getPayload() {
		// TODO Auto-generated method stub
		return this.payload;
	}

	
}
