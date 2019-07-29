package io.nzbee.ui.component.web.facet;

import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;


//this is a generic object used to populate UI elements such as navigation side-bars
//on the web-site, it can accept brand, category, or any other object as long as it's mapped
//in the service layer

public class NavFacet {

	private Long Id;

	private String facetingName;
	
	private String facetFieldName;
	
	private String facetClassName;

	private String facetDesc;
	
	private String facetToken;
	
	private String facetType;
	
	private List<NavFacetAttribute> navAttributes;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		this.Id = id;
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
	
	public List<NavFacetAttribute> getNavAttributes() {
		return navAttributes;
	}

	public void setNavAttributes(List<NavFacetAttribute> navAttributes) {
		this.navAttributes = navAttributes;
	}

	@Override
	public boolean equals(Object o) {
		 if (this == o) return true;
	     if (o == null || getClass() != o.getClass()) return false;
	     NavFacet pcf = (NavFacet) o;
	     return this.Id == pcf.Id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(Id);
	}

	@Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Facet [Id=").append(Id)
        .append(", facetDesc=").append(facetDesc)
        .append(", facetToken=").append(facetToken)
        .append(", facetingName=").append(facetingName)
        .append(", facetFieldName=").append(facetFieldName)
        .append(", attributes=").append(StringUtils.join(navAttributes, ","))
        .append("]");
        return builder.toString();
    }
}