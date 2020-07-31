package io.nzbee.resources.search;

import java.util.Objects;
import org.springframework.hateoas.RepresentationModel;

import io.nzbee.search.facet.IFacet;
import io.nzbee.search.facet.SearchFacetDiscrete;

public class SearchFacetResource extends RepresentationModel<SearchFacetResource> {

	private IFacet searchFacet;
	
	public SearchFacetResource(SearchFacetDiscrete searchFacet) {
		this.searchFacet = searchFacet;
	}

	public SearchFacetResource(IFacet searchFacet) {
		// TODO Auto-generated constructor stub
		this.searchFacet = searchFacet;
	}

	public IFacet getData() {
		return searchFacet;
	}
	
	@Override
	public boolean equals(Object o) {
		 if (this == o) return true;
	     if (o == null || getClass() != o.getClass()) return false;
	     SearchFacetDiscrete sf = (SearchFacetDiscrete) o;
	     return searchFacet.getValue() == sf.getValue();
	}

	@Override
	public int hashCode() {
		return Objects.hash(searchFacet.getValue());
	}
}
