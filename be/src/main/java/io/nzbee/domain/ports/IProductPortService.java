package io.nzbee.domain.ports;

import java.util.List;
import java.util.Set;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import io.nzbee.domain.product.Product;
import io.nzbee.search.dto.facet.IFacet;

public interface IProductPortService extends IProductDimensionService<Product> {

	Set<Product> findAll(String locale, String currency);

	Set<Product> findAll(String locale, String currency, List<String> productCodes);

	Page<Product> findAll(String locale, String currency, Double price, Pageable pageable, String categoryDesc,
			List<Product> collect, String sortBy);

	Page<Product> findAll(String locale, String currency, Pageable pageable, String categoryDesc, List<Product> collect,
			String sortBy);

	Page<Product> findAll(String locale, String currency, Double price, int page, int size, String categoryDesc,
			Set<IFacet> selectedFacets, String sortBy);

	Page<Product> findAll(String locale, String currency, String categoryDesc, int page, int size,
			Set<IFacet> selectedFacets, String sortBy);

	Page<Product> search(String locale, String currency, Pageable pageable, String category, String searchTerm,
			Set<IFacet> facetPayload, Set<IFacet> returnFacets);

	String[] getSuggestion(String searchTerm, String locale, String currency);
	
}
