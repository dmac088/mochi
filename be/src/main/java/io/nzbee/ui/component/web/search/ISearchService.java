package io.nzbee.ui.component.web.search;

import java.util.List;

import org.springframework.data.domain.Page;

import io.nzbee.domain.product.Product;
import io.nzbee.ui.component.web.facet.FacetContainer;
import io.nzbee.ui.component.web.facet.IFacet;

public interface ISearchService {

	String searchService = null;

	Page<Product> findAll(String locale, String currency, String categoryDesc, String searchTerm, int page, int size,
			String sortBy, FacetContainer selectedFacets);

	Search findAll(String lcl, String currency, String categoryDesc, String searchTerm, int page, int size,
			String sortBy, List<IFacet> facetPayload);


}
