package io.nzbee.resources.search;

import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import io.nzbee.resources.controllers.SearchController;
import io.nzbee.search.facet.IFacet;
import io.nzbee.search.facet.SearchFacet;

@Component
public class SearchFacetResourceAssembler extends RepresentationModelAssemblerSupport<SearchFacet, SearchFacetResource> {

	public SearchFacetResourceAssembler() {
		super(SearchController.class, SearchFacetResource.class);
	}
	
	@Override
	public SearchFacetResource toModel(SearchFacet searchFacet) {
		return new SearchFacetResource(searchFacet);
	}

	public Set<SearchFacetResource> toCollectionModel(Set<IFacet> returnFacets) {
		return returnFacets.stream().map(rf -> toModel((SearchFacet) rf)).collect(Collectors.toSet());
	}
    
}