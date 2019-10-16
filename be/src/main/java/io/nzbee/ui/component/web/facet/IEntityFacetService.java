package io.nzbee.ui.component.web.facet;

import io.nzbee.domain.IDomainObject;

public interface IEntityFacetService<T> {

	EntityFacetResult findAll(String locale, String currency);

	EntityFacetResult findAll(String locale, String currency, String category, EntityFacetContainer selectedFacets);

	EntityFacet<T> toEntityFacet(IDomainObject<T> dO);

	String calcFacetId(String className, String id);

	String calcToken(String className, String id);

	EntityFacetResult findAllBrands(String locale, String category);
}
