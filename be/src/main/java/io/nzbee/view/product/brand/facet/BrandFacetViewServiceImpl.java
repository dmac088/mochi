package io.nzbee.view.product.brand.facet;

import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import io.nzbee.view.ports.IBrandFacetViewPortService;

public class BrandFacetViewServiceImpl implements IBrandFacetViewService {

	@Autowired
	private IBrandFacetViewPortService brandService;
	
	@Override
	public List<BrandFacetView> findAll(String locale, String currency, String categoryCode, Set<String> categoryCodes,
			Set<String> brandCodes, Set<String> tagCodes, Double maxPrice) {
		return brandService.findAll(locale, currency, categoryCode, categoryCodes, tagCodes, maxPrice);
	}

	@Override
	public BrandFacetView findByCode(String locale, String rootCategory, String brandCode) {
		return brandService.findByCode(locale, rootCategory, brandCode);
	}

}
