package io.nzbee.search.facet;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("EntityFacet")
public class EntityFacet implements IFacet {

	private String Id;
	
	private String desc;
	
	private String facetingName;
	
	private String objectType;
	
	private String value;
	
	private boolean isHierarchical;
	
	private int count;
	
	public EntityFacet() {
		super();
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
	public Boolean isHierarchical() {
		return this.isHierarchical;
	}

	@Override
	public String getType() {
		return this.getClass().getSimpleName();
	}
	
	@Override
	public String getObjectType() {
		return this.objectType;
	}

	@Override
	public String getValue() {
		return this.value;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	@Override
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String getFacetingName() {
		return this.facetingName;
	}
	
	public void setId(String id) {
		Id = id;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public void setValue(String value) {
		this.value = value;
	}

	public void setHierarchical(boolean isHierarchical) {
		this.isHierarchical = isHierarchical;
	}
	
	public void setFacetingName(String name) {
		this.facetingName = name.toLowerCase();
	}

}
