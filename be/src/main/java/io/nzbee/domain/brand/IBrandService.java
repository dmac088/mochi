package io.nzbee.domain.brand;

import java.util.List;
import java.util.Set;
import io.nzbee.domain.ILocalizedService;

public interface IBrandService extends ILocalizedService<Brand> {
	
	List<Brand> findAll(String locale, String currency, String categoryCode, Set<String> categoryCodes,
			Set<String> brandCodes, Set<String> tagCodes, Double maxPrice);

	Brand findByCode(String locale, String brandCode);

	Brand findByProductCode(String locale, String productCode);
	
}
