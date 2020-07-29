package io.nzbee.search.dto.facet;

public interface IFacetMapper<T> {

	EntityFacet toEntityFacet(T object);
	
}
