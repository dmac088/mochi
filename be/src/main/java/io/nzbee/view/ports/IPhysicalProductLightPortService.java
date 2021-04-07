package io.nzbee.view.ports;

import java.util.List;
import java.util.Set;
import org.springframework.data.domain.Page;
import io.nzbee.entity.StringCollectionWrapper;
import io.nzbee.search.facet.IFacet;
import io.nzbee.view.product.physical.PhysicalProductLightView;

public interface IPhysicalProductLightPortService extends IPortService<PhysicalProductLightView> {

	Page<PhysicalProductLightView> findAll(String locale, String currency,
			String categoryCode, StringCollectionWrapper categoryCodes, StringCollectionWrapper brandCodes,
			StringCollectionWrapper tagCodes, Double maxPrice, String page, String size, String sort);

	Page<PhysicalProductLightView> search(String locale, String currency, String categoryCode, int page, int size, String sort, String searchTerm,
			Set<IFacet> selectedFacets, Set<IFacet> returnFacets);

	Page<PhysicalProductLightView> findAll(String locale, String currency, String categoryCode, Set<String> categoryCodes,
			Set<String> brandCodes, Set<String> tagCodes, Double maxPrice, String page, String size, String sort);
	
	Page<PhysicalProductLightView> findAllPhysicalProducts(String locale, String currency, String categoryCode, Set<String> categoryCodes,
			Set<String> brandCodes, Set<String> tagCodes, Double maxPrice, String page, String size, String sort);
	
	String[] getSuggestion(String searchTerm, String rootCategory, String locale, String currency);

	List<PhysicalProductLightView> findAll(String locale, String currency, String rootCategoryCode, Set<String> codes);

	List<PhysicalProductLightView> findAll(String locale, String currency, Set<String> codes);

	/*
	Page<ShippingProductView> findAllShippingProducts(String locale, String currency, String categoryCode, Set<String> categoryCodes,
			Set<String> brandCodes, Set<String> tagCodes, Double maxPrice, String page, String size, String sort);
	*/
}
