package io.nzbee.entity.brand;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import io.nzbee.entity.ILocalizedDao;

public interface IBrandDao extends ILocalizedDao<Brand> {
	
	Set<Brand> findAll(String locale,  Set<String> brandCodes); 
	
	Optional<Brand> findByProductCode(String locale, String productCode);

	Set<Brand> findAll(String locale, String currency, String categoryCode, Set<String> categoryCodes,
			Set<String> tagCodes, Double maxPrice);

	Set<Brand> findAllByCategory(String locale, String categoryCode);
}
