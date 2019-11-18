package io.nzbee.ui.component.web.search;

import java.util.Set;
import org.springframework.data.domain.Page;
import io.nzbee.domain.product.Product;
import io.nzbee.ui.component.web.facet.FacetContainer;
import io.nzbee.ui.component.web.facet.IFacet;

public interface ISearchService {

	Page<Product> findAll(String locale, String currency, String categoryDesc, String searchTerm, int page, int size,
			String sortBy, FacetContainer selectedFacets);

	Page<Product> findAll(String lcl, String currency, String categoryDesc, String searchTerm, int page, int size,
			String sortBy, Set<IFacet> facetPayload, Set<IFacet> returnFacets);

}
