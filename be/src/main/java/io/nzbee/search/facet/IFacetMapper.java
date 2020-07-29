package io.nzbee.search.facet;

public interface IFacetMapper<T> {

	EntityFacet toEntityFacet(T object);
	
}
