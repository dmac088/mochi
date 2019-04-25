package io.javabrains.springbootstarter.facets;

import java.util.Objects;

public class CategoryFacet {

	private String facetingName;
	
	private String facetFieldName;

	private Long Id;
	
	private String catgoryDesc;
	
	private String catgoryToken;
	
	private Long level;
	
	private Long count;
	
	private Long parentId;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		this.Id = id;
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

	public Long getLevel() {
		return level;
	}

	public void setLevel(Long categoryLevel) {
		this.level = categoryLevel;
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
	
	@Override
	public boolean equals(Object o) {
		 if (this == o) return true;
	     if (o == null || getClass() != o.getClass()) return false;
	     CategoryFacet pcf = (CategoryFacet) o;
	     return this.Id == pcf.Id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(Id);
	}

	@Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("CategoryDto [categoryId=")
        .append(Id)
        .append(", categoryDesc=").append(catgoryDesc);
        return builder.toString();
    }
}