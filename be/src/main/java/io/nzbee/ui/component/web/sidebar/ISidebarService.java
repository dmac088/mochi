package io.nzbee.ui.component.web.sidebar;

import java.util.List;

public interface ISidebarService {

	List<Sidebar> findAll(String locale, String currency, String category, List<Sidebar> selectedFacets);

}
