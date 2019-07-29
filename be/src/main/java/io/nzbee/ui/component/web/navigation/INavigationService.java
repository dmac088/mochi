package io.nzbee.ui.component.web.navigation;

import java.util.List;

import io.nzbee.ui.component.web.facet.NavFacet;
import io.nzbee.ui.component.web.search.Search;

public interface INavigationService {

	Search findAll(String locale, String currency, String categoryDesc, int page, int size, String sortBy,
			List<NavFacet> selectedFacets);

}
