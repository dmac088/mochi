package io.javabrains.springbootstarter.dto;

import java.util.Objects;

public class SidebarDTO {

	private String facetingName;
	
	private String facetFieldName;

	private Long Id;
	
	private String categoryDesc;
	
	private String categoryToken;
	
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
		return categoryDesc;
	}

	public void setDesc(String desc) {
		this.categoryDesc = desc;
	}
	
	public String getToken() {
		return categoryToken;
	}

	public void setToken(String categoryToken) {
		this.categoryToken = categoryToken;
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
	     SidebarDTO pcf = (SidebarDTO) o;
	     return this.Id == pcf.Id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(Id);
	}

	@Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("CategoryDto [Id=")
        .append(Id)
        .append(", categoryDesc=").append(categoryDesc)
        .append(", categoryToken=").append(categoryToken)
        .append(", facetingName=").append(facetingName)
        .append(", facetFieldName=").append(facetFieldName)
        .append(", count=").append(count)
        .append(", parentId=").append(parentId)
        .append("]");
        return builder.toString();
    }
}