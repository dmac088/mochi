package io.nzbee.resources.search;

import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import io.nzbee.resources.controllers.SearchController;
import io.nzbee.search.facet.IFacet;

@Component
public class SearchFacetModelAssembler extends RepresentationModelAssemblerSupport<IFacet, SearchFacetModel> {

	public SearchFacetModelAssembler() {
		super(SearchController.class, SearchFacetModel.class);
	}
	
	@Override
	public SearchFacetModel toModel(IFacet searchFacet) {
		return new SearchFacetModel(searchFacet);
	}

	public Set<SearchFacetModel> toCollectionModel(Set<IFacet> returnFacets) {
		return returnFacets.stream().map(rf -> toModel((IFacet) rf)).collect(Collectors.toSet());
	}
    
}