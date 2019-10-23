package io.nzbee.ui.component.web.search;

import java.util.List;

import io.nzbee.ui.component.web.facet.IFacet;
import io.nzbee.ui.component.web.facet.FacetContainer;
import io.nzbee.ui.component.web.facet.search.SearchFacet;

public interface ISearchService {

	String searchService = null;

	Search findAll(String locale, String currency, String categoryDesc, String searchTerm, int page, int size,
			String sortBy, FacetContainer selectedFacets);

//	Search findAll(String lcl, String currency, String categoryDesc, String searchTerm, int page, int size,
//			String sortBy, List<SearchFacet> facets);

	Search findAll(String lcl, String currency, String categoryDesc, String searchTerm, int page, int size,
			String sortBy, List<IFacet> selectedFacets);

}
