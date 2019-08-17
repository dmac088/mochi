package io.nzbee.ui.component.web.generic;

import java.util.List;
import java.util.stream.Collectors;

import io.nzbee.ui.component.web.facet.NavFacet;

public abstract class UIService {
	
	protected <T> List<String> getFacetTokens(List<NavFacet<T>> facets) {
		return facets.stream().map(c -> c.getToken()).collect(Collectors.toList());
	}
	
	protected <T> List<String> getFacetIds(List<NavFacet<T>> facets) {
		return facets.stream().filter(t -> {
			return t.getFacetClassName() != null && t.getFacetClassName().equals(t.getClass().getSimpleName());
		}).map(c -> c.getFacetId()).collect(Collectors.toList());
	}
	
}
