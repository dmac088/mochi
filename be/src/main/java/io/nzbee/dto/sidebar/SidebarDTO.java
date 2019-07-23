package io.nzbee.dto.sidebar;

import java.util.Objects;

public class SidebarDTO {

	private Long Id;

	private String facetingName;
	
	private String facetFieldName;
	
	private String facetClassName;

	private String facetDesc;
	
	private String facetToken;
	
	private String facetType;

	private Long level;
	
	private Long productCount;

	private boolean parent;

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

	public void setFacetingName(String facetingName) {
		this.facetingName = facetingName;
	}
	
	public String getFacetingClassName() {
		return facetClassName;
	}

	public void setFacetingClassName(String facetingClassName) {
		this.facetClassName = facetingClassName;
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

	public void setLevel(Long facetLevel) {
		this.level = facetLevel;
	}

	public String getDesc() {
		return facetDesc;
	}

	public void setDesc(String desc) {
		this.facetDesc = desc;
	}
	
	public String getToken() {
		return facetToken;
	}

	public void setToken(String facetToken) {
		this.facetToken = facetToken;
	}
	
	public String getFacetType() {
		return facetType;
	}

	public void setFacetType(String facetType) {
		this.facetType = facetType;
	}

	public Long getProductCount() {
		return productCount;
	}

	public void setProductCount(Long productCount) {
		this.productCount = productCount;
	}

	public boolean isParent() {
		return parent;
	}

	public void setParent(boolean parent) {
		this.parent = parent;
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
        .append(", facetDesc=").append(facetDesc)
        .append(", facetToken=").append(facetToken)
        .append(", facetingName=").append(facetingName)
        .append(", facetFieldName=").append(facetFieldName)
        .append(", count=").append(productCount)
        .append(", parentId=").append(parentId)
        .append("]");
        return builder.toString();
    }
}