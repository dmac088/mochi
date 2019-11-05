package io.nzbee.ui.component.web.navigation;

import org.springframework.data.domain.Page;

import io.nzbee.domain.product.Product;
import io.nzbee.ui.component.web.facet.FacetContainer;

public interface INavigationService {

	Page<Product> findAll(String locale, String currency, String categoryDesc, int page, int size,
			FacetContainer selectedFacets, String sortBy);

	Page<Product> findAll(String locale, String currency, Double price, int page, int size, String categoryDesc,
			FacetContainer selectedFacets, String sortBy);

}
