package io.nzbee.ui.component.web.facet;

import java.util.Objects;

//this is a generic object used to populate UI elements such as navigation side-bars
//on the web-site, it can accept brand, category, or any other object as long as it's mapped
//in the service layer

public class NavFacet<T> {

	private Long Id;

	private String facetName;
	
	private String facetToken;
	
	private String facetType;
	
	private String facetDisplayValue;

	private String facetClassName;
	
	private Long productCount;

	private T payload;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		this.Id = id;
	}
	
	public String getFacetName() {
		return facetName;
	}

	public void setFacetName(String facetName) {
		this.facetName = facetName;
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
		return this.getPayload().getClass().getSimpleName();
	}
	
	public Long getProductCount() {
		return productCount;
	}

	public void setProductCount(Long productCount) {
		this.productCount = productCount;
	}

	@Override
	public boolean equals(Object o) {
		 if (this == o) return true;
	     if (o == null || getClass() != o.getClass()) return false;
	     NavFacet<T> pcf = (NavFacet<T>) o;
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
        .append(", facetToken=").append(facetToken)
        .append(", facetName=").append(facetName)
        .append(", facetClassName=").append(facetClassName)
        .append("]");
        return builder.toString();
    }

}