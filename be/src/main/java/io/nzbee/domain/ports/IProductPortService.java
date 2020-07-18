package io.nzbee.domain.ports;

import java.util.List;
import java.util.Set;
import org.springframework.data.domain.Page;
import io.nzbee.domain.product.Product;
import io.nzbee.search.dto.facet.IFacet;

public interface IProductPortService extends IProductDimensionService<Product> {

	Set<Product> findAll(String locale, String currency);

	Set<Product> findAll(String locale, String currency, List<String> productCodes);

	String[] getSuggestion(String searchTerm, String locale, String currency);

	<T> Set<Product> findAllByType(String locale, String currency, Class<T> cls);

	Page<Product> search(String locale, String currency, String categoryCode, int page, int size, String searchTerm,
			Set<IFacet> selectedFacets, Set<IFacet> returnFacets);

	Page<Product> findAll(String locale, String currency, String categoryCode, Set<String> categoryCodes,
			Set<String> brandCodes, Set<String> tagCodes, Double maxPrice, String page, String size, String sort);

}
