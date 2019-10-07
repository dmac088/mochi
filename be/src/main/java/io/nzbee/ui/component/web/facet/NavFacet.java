package io.nzbee.ui.component.web.facet;

import java.util.Objects;

//this is a generic object used to populate UI elements such as navigation side-bars
//on the web-site, it can accept brand, category, or any other object as long as it's mapped
//in the service layer

public class NavFacet<T> {

	private String facetId;
	
	private String facetToken;
	
	private String facetType;
	
	private String facetDisplayValue;

	private String facetClassName;

	private T payload;

	public String getFacetId() {
		return facetId;
	}

	public void setFacetId(String id) {
		this.facetId = id;
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

	public String getFacetDisplayValue() {
		return facetDisplayValue;
	}

	public void setFacetDisplayValue(String facetDisplayValue) {
		this.facetDisplayValue = facetDisplayValue;
	}

	public T getPayload() {
		return payload;
	}

	public void setPayload(T payload) {
		this.payload = payload;
	}

	public String getFacetClassName() {
		return this.facetClassName;
	}
	
	public void setFacetClassName(String facetClassName) {
		this.facetClassName = facetClassName;
	}

	@Override
	public boolean equals(Object o) {
		 if (this == o) return true;
	     if (o == null || getClass() != o.getClass()) return false;
	     if(o instanceof NavFacet<?> ) {
	    	 return this.facetToken == ((NavFacet<?>) o).facetToken;
	     }
	     return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(facetId);
	}

	@Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Facet [Id=").append(facetId)
        .append(", facetToken=").append(facetToken)
        .append(", facetClassName=").append(facetClassName)
        .append("]");
        return builder.toString();
    }

}