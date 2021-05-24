package io.nzbee.search.facet;

public interface IFacetHierarchical extends IFacet {

	int getChildCount();
	
	int getLevel();
	
}
