package io.nzbee.search.facet;

import io.nzbee.enums.FacetNameEnum;

public class EntityFacetHierarchical implements IFacetHierarchical {

	private String Id;
	
	private String parentId;
	
	private String desc;
	
	private FacetNameEnum facetingName;
	
	private String objectType;
	
	private String value;
	
	private boolean isHierarchical;
	
	private int count;
	
	private int childCount;
	
	private int level;
	
	@Override
	public String getId() {
		return Id;
	}
	
	@Override
	public String getParentId() {
		return parentId;
	}

	@Override
	public String getDesc() {
		return desc;
	}

	@Override
	public Boolean isHierarchical() {
		return isHierarchical;
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
	public String getFacetingName() {
		return this.facetingName.toString();
	}

	@Override
	public int getCount() {
		return this.count;
	}

	@Override
	public String getValue() {
		return this.value;
	}

	@Override
	public int getChildCount() {
		return this.childCount;
	}

	@Override
	public int getLevel() {
		return this.level;
	}

	public void setId(String id) {
		Id = id;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public void setFacetingName(FacetNameEnum category) {
		this.facetingName = category;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setHierarchical(boolean isHierarchical) {
		this.isHierarchical = isHierarchical;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void setChildCount(int childCount) {
		this.childCount = childCount;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
	

}
