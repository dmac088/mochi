package io.nzbee.ui.component.web.search;

import java.util.List;
import org.springframework.data.domain.Page;
import io.nzbee.domain.Product;
import io.nzbee.ui.component.web.facet.NavFacet;
import io.nzbee.ui.component.web.facet.NavFacetContainer;

public interface ISearchService {

	String searchService = null;

	NavFacet<Object> getMaxPrice(String categoryDesc, String locale, String currency, NavFacetContainer selectedFacets);

	Search findAll(String locale, String currency, String categoryDesc, String searchTerm, int page, int size,
			String sortBy, NavFacetContainer selectedFacets);

	Search findAll(String lcl, String currency, String categoryDesc, String searchTerm, int page, int size,
			String sortBy, List<String> categoryTokens, List<String> brandTokens, List<String> tagTokens,
			List<String> priceTokens);

}
