package io.nzbee.search.dto.facet;

import com.fasterxml.jackson.annotation.JsonTypeName;
import io.nzbee.search.ISearchDimension;

@JsonTypeName("EntityFacet")
public class EntityFacet implements IFacet {

	private ISearchDimension payload;

	private String Id;
	
	private String desc;
	
	private String facetingName;
	
	private String payloadType;
	
	private String value;
	
	public EntityFacet() {
		super();
	}

	public EntityFacet(ISearchDimension entity) {
		this.payload = entity;
		this.facetingName = entity.getClass().getSimpleName().toLowerCase().trim();
		this.Id = entity.getCode();
		this.value = entity.getCode();
		this.desc = entity.getDesc();
		this.payloadType = entity.getClass().getSimpleName();
	}
	
	@Override
	public String getId() {
		return this.Id;
	}

	@Override
	public String getDesc() {
		return this.desc;
	}

	@Override
	public boolean isHierarchical() {
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
		return this.payloadType;
	}

	@Override
	public String getValue() {
		return this.value;
	}

	@Override
	public int getCount() {
		return this.payload.getCount();
	}

	@Override
	public String getFacetingName() {
		return this.facetingName;
	}
	
	public void setFacetingName(String name) {
		this.facetingName = name;
	}
}
