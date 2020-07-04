package io.nzbee.domain.brand;

import java.util.Set;
import io.nzbee.domain.ILocalizedService;

public interface IBrandService extends ILocalizedService<Brand> {
	
	Set<Brand> findAll(String locale, String currency, String category);
	
	Brand findByProductCode(String locale, String currency, String productCode);

	Set<Brand> findAll(String locale, String currency, String categoryCode, Set<String> categoryCodes,
			Set<String> tagCodes, Double maxPrice);
	
}
