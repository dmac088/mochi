package io.nzbee.domain.ports;

import java.util.List;
import java.util.Set;
import org.springframework.data.domain.Page;
import io.nzbee.domain.product.Product;
import io.nzbee.domain.product.physical.PhysicalProduct;
import io.nzbee.domain.product.shipping.ShippingProduct;
import io.nzbee.search.facet.IFacet;

public interface IProductPortService extends IPortService<Product> {

	List<Product> findAll(String locale, String currency);

	<T> List<Product> findAllByType(String locale, String currency, Class<T> cls);

	Page<Product> search(String locale, String currency, String categoryCode, int page, int size, String sort, String searchTerm,
			Set<IFacet> selectedFacets, Set<IFacet> returnFacets);

	Page<Product> findAll(String locale, String currency, String categoryCode, Set<String> categoryCodes,
			Set<String> brandCodes, Set<String> tagCodes, Double maxPrice, String page, String size, String sort);
	
	Page<PhysicalProduct> findAllPhysicalProducts(String locale, String currency, String categoryCode, Set<String> categoryCodes,
			Set<String> brandCodes, Set<String> tagCodes, Double maxPrice, String page, String size, String sort);
	
	Page<ShippingProduct> findAllShippingProducts(String locale, String currency, String categoryCode, Set<String> categoryCodes,
			Set<String> brandCodes, Set<String> tagCodes, Double maxPrice, String page, String size, String sort);

	List<Product> findAll(String locale, String currency, Set<String> codes);

	Product findByDesc(String locale, String currency, String desc);

	Product findByCode(String locale, String currency, String code);

	String[] getSuggestion(String searchTerm, String rootCategory, String locale, String currency);

}
