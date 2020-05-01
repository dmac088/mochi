package io.nzbee.entity.brand;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import io.nzbee.entity.ILocalizedDao;

public interface IBrandDao extends ILocalizedDao<Brand> {

	List<Brand> findAll(String locale, String currency, Set<String> categoryCodes, Set<String> tagCodes);
	
	List<Brand> findAll(String locale, String currency, Set<String> brandCodes);

	List<Brand> findAllByCategory(String locale, String currency, String categoryCode);
	
	Optional<Brand> findByProductCode(String locale, String currency, String productCode);
}
