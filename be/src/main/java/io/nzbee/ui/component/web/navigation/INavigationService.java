package io.nzbee.ui.component.web.navigation;

import io.nzbee.ui.component.web.facet.NavFacetContainer;
import io.nzbee.ui.component.web.search.Search;

public interface INavigationService {

	Search findAll(String locale, String currency, String categoryDesc, int page, int size,
			NavFacetContainer selectedFacets, String sortBy);

	Search findAll(String locale, String currency, Double price, int page, int size, String categoryDesc,
			NavFacetContainer selectedFacets, String sortBy);

}
