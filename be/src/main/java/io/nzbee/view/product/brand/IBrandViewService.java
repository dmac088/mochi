package io.nzbee.view.product.brand;

import java.util.List;
import java.util.Set;
import io.nzbee.view.IViewService;

public interface IBrandViewService extends IViewService<BrandView> {
	
	List<BrandView> findAll(String locale, String currency, String categoryCode, Set<String> categoryCodes,
			Set<String> brandCodes, Set<String> tagCodes, Double maxPrice);

	BrandView findByCode(String locale, String brandCode);

	
}
