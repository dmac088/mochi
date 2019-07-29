package io.nzbee.ui.component.web.navigation;

import java.util.List;

import io.nzbee.ui.component.web.search.Search;
import io.nzbee.ui.component.web.sidebar.Sidebar;

public interface INavigationService {

	Search findAll(String locale, String currency, String categoryDesc, int page, int size, String sortBy,
			List<Sidebar> selectedFacets);

}
