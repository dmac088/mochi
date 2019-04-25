package io.javabrains.springbootstarter.facets;

public class CategoryFacet {

	private String facetingName;
	
	private String facetFieldName;

	private String value;
	
	private Long id;
	
	private String catgoryDesc;
	
	private String catgoryToken;
	
	private Long categoryLevel;
	
	private Long count;
	
	private Long parentId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	
	public String getFacetingName() {
		return facetingName;
	}

	public void setName(String facetingName) {
		this.facetingName = facetingName;
	}

	public String getFieldName() {
		return facetFieldName;
	}

	public void setFieldName(String facetFieldName) {
		this.facetFieldName = facetFieldName;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Long getCategoryLevel() {
		return categoryLevel;
	}

	public void setCategoryLevel(Long categoryLevel) {
		this.categoryLevel = categoryLevel;
	}

	public String getDesc() {
		return catgoryDesc;
	}

	public void setDesc(String desc) {
		this.catgoryDesc = desc;
	}
	
	public String getToken() {
		return catgoryToken;
	}

	public void setToken(String catgoryToken) {
		this.catgoryToken = catgoryToken;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}
}