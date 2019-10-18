package io.nzbee.ui.component.web.search;

import java.util.List;

import io.nzbee.ui.component.web.search.facet.IFacet;
import io.nzbee.ui.component.web.search.facet.SearchFacet;
import io.nzbee.ui.component.web.search.facet.SearchFacetContainer;

public interface ISearchService {

	String searchService = null;

	Search findAll(String locale, String currency, String categoryDesc, String searchTerm, int page, int size,
			String sortBy, SearchFacetContainer selectedFacets);

//	Search findAll(String lcl, String currency, String categoryDesc, String searchTerm, int page, int size,
//			String sortBy, List<SearchFacet> facets);

	Search findAll(String lcl, String currency, String categoryDesc, String searchTerm, int page, int size,
			String sortBy, List<IFacet> selectedFacets);

}
