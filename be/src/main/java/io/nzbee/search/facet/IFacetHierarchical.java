package io.nzbee.search.facet;

public interface IFacetHierarchical extends IFacet {

	String getParentId();
	
	int getChildCount();
	
	int getLevel();
	
}
