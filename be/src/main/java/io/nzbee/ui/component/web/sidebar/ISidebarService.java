package io.nzbee.ui.component.web.sidebar;

import java.util.List;

public interface ISidebarService {

	List<Sidebar> findAllTags(String locale, String currency, String category, List<Sidebar> selectedFacets);
	
	List<Sidebar> findAllCategories(String locale, String currency, String category, List<Sidebar> selectedFacets);
}
