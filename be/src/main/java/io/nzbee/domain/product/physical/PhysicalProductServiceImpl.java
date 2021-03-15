package io.nzbee.domain.product.physical;

import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import io.nzbee.domain.ports.IProductPortService;

public class PhysicalProductServiceImpl implements IPhysicalProductService {
	
	@Autowired
	private IProductPortService productService;

	@Override
	public Page<PhysicalProduct> findAll(String locale, String currency, String categoryCode, Set<String> categoryCodes,
			Set<String> brandCodes, Set<String> tagCodes, Double maxPrice, String page, String size, String sort) {
		return productService.findAllPhysicalProducts(locale, currency, categoryCode, categoryCodes, brandCodes, tagCodes, maxPrice, page, size, sort);
	}

}
