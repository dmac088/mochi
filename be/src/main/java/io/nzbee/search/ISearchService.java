package io.nzbee.search;

import java.util.Set;
import org.springframework.data.domain.Page;
import io.nzbee.entity.product.Product;
import io.nzbee.search.dto.facet.IFacet;

public interface ISearchService {
	
	Page<Product> findAll(String lcl, String currency, String categoryDesc, String searchTerm, int page, int size,
			String sortBy, Set<IFacet> facetPayload, Set<IFacet> returnFacets);

	String[] getSuggestions(String searchTerm, String locale, String currency);
}
