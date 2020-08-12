package io.nzbee.entity.brand;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import io.nzbee.entity.ILocalizedDao;

public interface IBrandDao extends ILocalizedDao<Brand> {
	
	List<Brand> findAll(String locale,  Set<String> brandCodes); 
	
	Optional<Brand> findByProductCode(String locale, String productCode);

	List<Brand> findAll(String locale, String currency, String categoryCode, Set<String> categoryCodes,
			Set<String> tagCodes, Double maxPrice);

	List<Brand> findAllByCategory(String locale, String categoryCode);
}
