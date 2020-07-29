package io.nzbee.domain.ports;

import java.util.Set;

import org.springframework.data.domain.Page;
import io.nzbee.domain.product.Product;
import io.nzbee.search.facet.IFacet;

public interface INavigationService {
	
	Page<Product> findAll(String locale, String currency, Double price, int page, int size, String categoryDesc,
			Set<IFacet> selectedFacets, String sortBy);

	Page<Product> findAll(String locale, String currency, String categoryDesc, int page, int size,
			Set<IFacet> selectedFacets, String sortBy);

}
