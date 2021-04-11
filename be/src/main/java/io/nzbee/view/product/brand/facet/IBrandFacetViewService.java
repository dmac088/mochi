package io.nzbee.view.product.brand.facet;

import java.util.List;
import java.util.Set;
import io.nzbee.view.IViewService;

public interface IBrandFacetViewService extends IViewService<BrandFacetView> {
	
	List<BrandFacetView> findAll(String locale, String currency, String categoryCode, Set<String> categoryCodes,
			Set<String> brandCodes, Set<String> tagCodes, Double maxPrice);

	BrandFacetView findByCode(String locale, String rootCategory, String brandCode);
	
}
