package io.nzbee.ui.component.web.navigation;

import io.nzbee.ui.component.web.facet.NavFacetContainer;
import io.nzbee.ui.component.web.search.Search;

public interface INavigationService {

	Search findAll(String locale, String currency, String categoryDesc, Double price, int page, int size, String sortBy,
			NavFacetContainer selectedFacets);

}
