package io.nzbee.search.facet;

public interface IFacetMapper<T, E> {

	E toEntityFacet(T object);
	
}
