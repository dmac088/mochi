package io.nzbee.resources.dto.search;

import org.springframework.hateoas.RepresentationModel;
import io.nzbee.search.dto.facet.SearchFacet;

public class SearchFacetResource extends RepresentationModel<SearchFacetResource> {

private final SearchFacet searchFacet;
	
	public SearchFacetResource(SearchFacet searchFacet) {
		this.searchFacet = searchFacet;
	}

	public SearchFacet getData() {
		return searchFacet;
	}
	
	
}
