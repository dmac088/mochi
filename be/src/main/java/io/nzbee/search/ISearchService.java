package io.nzbee.search;

import java.util.Set;
import org.springframework.data.domain.Page;
import io.nzbee.entity.product.ProductEntity;
import io.nzbee.search.facet.IFacet;

public interface ISearchService {
	
	Page<ProductEntity> findAll(String lcl, String currency, String categoryDesc, String searchTerm, int page, int size,
			String sortBy, Set<IFacet> facetPayload, Set<IFacet> returnFacets);

	String[] getSuggestions(String searchTerm, String locale, String currency);
}
