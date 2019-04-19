package io.javabrains.springbootstarter.services;


public class CustomFacet {

	private String facetingName;
	
	private String facetFieldName;

	private String value;

	public String getFacetingName() {
		return facetingName;
	}

	public void setFacetingName(String facetingName) {
		this.facetingName = facetingName;
	}

	public String getFacetFieldName() {
		return facetFieldName;
	}

	public void setFacetFieldName(String facetFieldName) {
		this.facetFieldName = facetFieldName;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
