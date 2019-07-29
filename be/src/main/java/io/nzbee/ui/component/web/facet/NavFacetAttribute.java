package io.nzbee.ui.component.web.facet;

public class NavFacetAttribute {

	private Long Id;
	
	private String attributeName;
	
	private String attributeValue;

	private NavFacet sidebar;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	public String getAttributeValue() {
		return attributeValue;
	}

	public void setAttributeValue(String attributeValue) {
		this.attributeValue = attributeValue;
	}
	
	public NavFacet getSidebar() {
		return sidebar;
	}

	public void setSidebar(NavFacet sidebar) {
		this.sidebar = sidebar;
	}
}
