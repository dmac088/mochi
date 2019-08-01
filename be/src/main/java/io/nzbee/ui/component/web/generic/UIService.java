package io.nzbee.ui.component.web.generic;

import java.util.List;
import java.util.stream.Collectors;

import io.nzbee.ui.component.web.facet.NavFacet;

public abstract class UIService {

	protected <T> List<String> getFacetTokens(List<NavFacet> facets, Class<T> type) {
		return facets.stream().filter(t -> t.getFacetClassName().equals(type.getSimpleName())).map(c -> c.getToken()).collect(Collectors.toList());
	}
	
	protected <T> List<Long> getFacetIds(List<NavFacet> facets, Class<T> type) {
		return facets.stream().filter(t -> {
			System.out.println(t.getFacetClassName());
			System.out.println(type.getSimpleName());
			return t.getFacetClassName() != null && t.getFacetClassName().equals(type.getSimpleName());
		}).map(c -> c.getId()).collect(Collectors.toList());
		
	}
	
}
