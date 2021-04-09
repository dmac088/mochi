package io.nzbee.entity.brand;

import java.util.List;
import java.util.Optional;
import io.nzbee.entity.ILightLocalizedService;
import io.nzbee.entity.StringCollectionWrapper;
import io.nzbee.entity.brand.domain.BrandDomainDTO;
import io.nzbee.search.ISearchDimensionService;

public interface IBrandService extends ILightLocalizedService<BrandDomainDTO, BrandEntity>, ISearchDimensionService<BrandDomainDTO> {
	
	List<BrandDomainDTO> findAll(String locale, String currency, String caetgoryCode, StringCollectionWrapper categoryCodes,
			StringCollectionWrapper tagCodes, Double maxPrice);

	List<BrandDomainDTO> findAll(String locale, String rootCategory, String categoryCode);

	Optional<BrandDomainDTO> findByCode(String locale, String rootCategory, String code);

	Optional<BrandDomainDTO> findByDesc(String locale, String rootCategory, String desc);

	List<BrandDomainDTO> findAllByProductType(String locale, Class<?> cls);

	List<BrandEntity> findAll();

	Optional<BrandEntity> findByCode(String code);

	Optional<BrandEntity> findById(Long id);

}
