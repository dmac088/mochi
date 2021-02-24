package io.nzbee.entity.brand;

import java.util.List;
import java.util.Optional;
import io.nzbee.entity.ILocalizedDao;
import io.nzbee.entity.StringCollectionWrapper;

public interface IBrandDao extends ILocalizedDao<BrandDTO, BrandEntity> {

	Optional<BrandEntity> findByCode(String code);
	
	Optional<BrandDTO> findByDesc(String locale, String rootCategory, String desc);

	List<BrandDTO> findAll(String locale, String currency, String categoryCode, StringCollectionWrapper categoryCodes,
			StringCollectionWrapper tagCodes, Double maxPrice);

	List<BrandDTO> findAll(String locale, String rootCategory);

	Optional<BrandDTO> findByProductCode(String locale, String rootCategory, String productCode);

	List<BrandDTO> findAllByCategory(String locale, String rootCategory, String categoryCode);

	List<BrandDTO> findAll(String locale, String rootCategory, StringCollectionWrapper codes);

	Optional<BrandDTO> findByCode(String locale, String rootCategory, String code);

	Optional<BrandDTO> findById(String locale, String rootCategory, Long id);

	
}
