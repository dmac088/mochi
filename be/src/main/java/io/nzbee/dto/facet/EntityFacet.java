package io.nzbee.dto.facet;

import com.fasterxml.jackson.annotation.JsonTypeName;

import io.nzbee.domain.ISearchDimension;

@JsonTypeName("EntityFacet")
public class EntityFacet implements IFacet {

	private final ISearchDimension payload;

	public EntityFacet(ISearchDimension entity) {
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
	public ISearchDimension getPayload() {
		return this.payload;
	}

	@Override
	public String getType() {
		return this.getClass().getSimpleName();
	}
	
	@Override
	public String getPayloadType() {
		return this.getPayload().getClass().getSimpleName();
	}

	@Override
	public String getValue() {
		return "Empty";
	}

	@Override
	public int getCount() {
		return 0;
	}

	@Override
	public String getFacetingName() {
		return this.getPayload().getClass().getSimpleName().toLowerCase().trim();
	}
	
}
