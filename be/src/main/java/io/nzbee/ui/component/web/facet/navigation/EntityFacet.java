package io.nzbee.ui.component.web.facet.navigation;

import io.nzbee.domain.IDomainObject;
import io.nzbee.ui.component.web.facet.Facet;

public class EntityFacet extends Facet {

	private final IDomainObject payload;

	public EntityFacet(IDomainObject entity) {
		this.payload = entity;
		this.setName(this.getClass().getSimpleName());
	}
	
	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return this.payload.getCode();
	}

	@Override
	public String getDisplayValue() {
		// TODO Auto-generated method stub
		return this.payload.getDesc() + " (" + this.payload.getCount() + ")";
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

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return this.getClass().getSimpleName();
	}
	
	@Override
	public String getPayloadType() {
		// TODO Auto-generated method stub
		return this.getPayload().getClass().getSimpleName();
	}

	@Override
	protected String getValue() {
		// TODO Auto-generated method stub
		return "Empty";
	}
	
}
