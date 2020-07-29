package io.nzbee.resources.search;

import java.util.Objects;
import org.springframework.hateoas.RepresentationModel;

import io.nzbee.search.facet.SearchFacet;

public class SearchFacetResource extends RepresentationModel<SearchFacetResource> {

	private SearchFacet searchFacet;
	
	public SearchFacetResource(SearchFacet searchFacet) {
		this.searchFacet = searchFacet;
	}

	public SearchFacet getData() {
		return searchFacet;
	}
	
	@Override
	public boolean equals(Object o) {
		 if (this == o) return true;
	     if (o == null || getClass() != o.getClass()) return false;
	     SearchFacet sf = (SearchFacet) o;
	     return searchFacet.getValue() == sf.getValue();
	}

	@Override
	public int hashCode() {
		return Objects.hash(searchFacet.getValue());
	}
}
