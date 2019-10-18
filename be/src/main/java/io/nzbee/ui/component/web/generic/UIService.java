package io.nzbee.ui.component.web.generic;

import java.util.List;
import java.util.stream.Collectors;
import io.nzbee.ui.component.web.search.facet.SearchFacet;

public abstract class UIService {
	
	protected <T> List<String> getFacetTokens(List<SearchFacet> facets) {
		return facets.stream().map(c -> c.getValue()).collect(Collectors.toList());
	}
	
	protected <T> List<String> getFacetIds(List<SearchFacet> facets) {
		return facets.stream().filter(t -> {
			return t.getEntity().getClass().equals(t.getClass());
		}).map(c -> c.getCode()).collect(Collectors.toList());
	}
	
}
