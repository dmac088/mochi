package io.nzbee.ui.component.web.navigation;

import io.nzbee.ui.component.web.search.Search;
import io.nzbee.ui.component.web.search.facet.SearchFacetContainer;

public interface INavigationService {

	Search findAll(String locale, String currency, String categoryDesc, int page, int size,
			SearchFacetContainer selectedFacets, String sortBy);

	Search findAll(String locale, String currency, Double price, int page, int size, String categoryDesc,
			SearchFacetContainer selectedFacets, String sortBy);

}
