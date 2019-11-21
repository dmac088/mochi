package io.nzbee.ui.component.web.facet.navigation;

import com.fasterxml.jackson.annotation.JsonTypeName;

import io.nzbee.domain.IDomainObject;
import io.nzbee.ui.component.web.facet.IFacet;

@JsonTypeName("EntityFacet")
public class EntityFacet implements IFacet {

	private final IDomainObject payload;

	public EntityFacet(IDomainObject entity) {
		this.payload = entity;
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
	public String getValue() {
		// TODO Auto-generated method stub
		return "Empty";
	}
	
}
