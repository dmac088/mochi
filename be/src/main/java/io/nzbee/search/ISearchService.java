package io.nzbee.search;

import java.util.Set;
import org.springframework.data.domain.PageImpl;
import io.nzbee.search.facet.IFacet;
import io.nzbee.view.product.physical.light.PhysicalProductLightView;

public interface ISearchService {
	
	PageImpl<PhysicalProductLightView> findAll(String lcl, String currency, String categoryDesc, String searchTerm, int page, int size,
			String sortBy, Set<IFacet> facetPayload, Set<IFacet> returnFacets);

	String[] getSuggestions(String searchTerm, String rootCategory, String locale, String currency);
}
