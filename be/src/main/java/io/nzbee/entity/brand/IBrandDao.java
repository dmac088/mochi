package io.nzbee.entity.brand;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import io.nzbee.entity.ILocalizedDao;

public interface IBrandDao extends ILocalizedDao<BrandDTO, BrandEntity> {
	
	List<BrandDTO> findAll(String locale,  Set<String> brandCodes); 
	
	Optional<BrandDTO> findByProductCode(String locale, String productCode);

	List<BrandDTO> findAll(String locale, String currency, String categoryCode, Set<String> categoryCodes,
			Set<String> tagCodes, Double maxPrice);

	List<BrandDTO> findAllByCategory(String locale, String categoryCode);

	Optional<BrandEntity> findByCode(String code);
}
