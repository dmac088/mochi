package io.nzbee.ui.component.web.generic;

import java.util.List;
import java.util.stream.Collectors;

import io.nzbee.ui.component.web.sidebar.Sidebar;

public abstract class UIService {

	protected <T> List<String> getFacetTokens(List<Sidebar> facets, Class<T> type) {
		return facets.stream().filter(t -> t.getFacetingClassName().equals(type.getSimpleName())).map(c -> c.getToken()).collect(Collectors.toList());
	}
	
	protected <T> List<Long> getFacetIds(List<Sidebar> facets, Class<T> type) {
		return facets.stream().filter(t -> t.getFacetingClassName().equals(type.getSimpleName())).map(c -> c.getId()).collect(Collectors.toList());
	}
	
}
