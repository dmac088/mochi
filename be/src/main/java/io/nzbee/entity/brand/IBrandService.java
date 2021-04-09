package io.nzbee.entity.brand;

import java.util.List;
import java.util.Optional;
import io.nzbee.entity.ILightLocalizedService;
import io.nzbee.entity.StringCollectionWrapper;
import io.nzbee.search.ISearchDimensionService;

public interface IBrandService extends ILightLocalizedService<BrandDTO, BrandEntity>, ISearchDimensionService<BrandDTO> {
	
	List<BrandDTO> findAll(String locale, String currency, String caetgoryCode, StringCollectionWrapper categoryCodes,
			StringCollectionWrapper tagCodes, Double maxPrice);

	List<BrandDTO> findAll(String locale, String rootCategory, String categoryCode);

	Optional<BrandDTO> findByCode(String locale, String rootCategory, String code);

	Optional<BrandDTO> findByDesc(String locale, String rootCategory, String desc);

	List<BrandDTO> findAllByProductType(String locale, Class<?> cls);

	List<BrandEntity> findAll();

	Optional<BrandEntity> findByCode(String code);

	Optional<BrandEntity> findById(Long id);

}
