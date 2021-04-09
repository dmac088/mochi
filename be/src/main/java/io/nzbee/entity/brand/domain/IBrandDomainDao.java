package io.nzbee.entity.brand.domain;

import java.util.List;
import java.util.Optional;
import io.nzbee.entity.ILocalizedDao;
import io.nzbee.entity.StringCollectionWrapper;
import io.nzbee.entity.brand.BrandEntity;

public interface IBrandDomainDao extends ILocalizedDao<BrandDomainDTO, BrandEntity> {

	Optional<BrandEntity> findByCode(String code);
	
	Optional<BrandDomainDTO> findByDesc(String locale, String rootCategory, String desc);

	List<BrandDomainDTO> findAll(String locale, String currency, String categoryCode, StringCollectionWrapper categoryCodes,
			StringCollectionWrapper tagCodes, Double maxPrice);

	List<BrandDomainDTO> findAll(String locale, String rootCategory);

	Optional<BrandDomainDTO> findByProductCode(String locale, String rootCategory, String productCode);

	List<BrandDomainDTO> findAllByCategory(String locale, String rootCategory, String categoryCode);

	List<BrandDomainDTO> findAll(String locale, String rootCategory, StringCollectionWrapper codes);

	Optional<BrandDomainDTO> findByCode(String locale, String rootCategory, String code);

	Optional<BrandDomainDTO> findById(String locale, String rootCategory, Long id);

	
}
