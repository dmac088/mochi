package io.nzbee.ui.component.web.search.facet;

import io.nzbee.domain.IDomainObject;

public interface ISearchFacetService<T> {

	SearchFacetResult findAll(String locale, String currency);

	SearchFacetResult findAll(String locale, String currency, String category, SearchFacetContainer selectedFacets);

	SearchFacet<T> toEntityFacet(IDomainObject<T> dO);

	String calcFacetId(String className, String id);

	String calcToken(String className, String id);

	SearchFacetResult findAllBrands(String locale, String category);
}
