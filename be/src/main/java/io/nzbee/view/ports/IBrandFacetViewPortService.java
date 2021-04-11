package io.nzbee.view.ports;

import java.util.List;
import java.util.Set;
import io.nzbee.view.product.brand.facet.BrandFacetView;

public interface IBrandFacetViewPortService extends IViewPortService<BrandFacetView> {

	List<BrandFacetView> findAll(String locale, String currency, String categoryCode, Set<String> categoryCodes,
			Set<String> tagCodes, Double maxPrice);

	BrandFacetView findByCode(String locale, String rootCategory, String brandCode);

}
