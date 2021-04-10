package io.nzbee.view.product.brand;

import java.util.List;
import java.util.Set;

import io.nzbee.view.ports.IBrandViewPortService;

public class BrandViewServiceImpl implements IBrandViewService {

	private IBrandViewPortService brandService;
	
	@Override
	public List<BrandView> findAll(String locale, String currency, String categoryCode, Set<String> categoryCodes,
			Set<String> brandCodes, Set<String> tagCodes, Double maxPrice) {
		return brandService.findAll(locale, currency, categoryCode, categoryCodes, tagCodes, maxPrice);
	}

	@Override
	public BrandView findByCode(String locale, String brandCode) {
		return brandService.findByCode(locale, brandCode);
	}

	@Override
	public void save(BrandView object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(BrandView object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(BrandView object) {
		// TODO Auto-generated method stub
		
	}
	
}
