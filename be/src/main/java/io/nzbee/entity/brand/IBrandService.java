package io.nzbee.entity.brand;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import io.nzbee.entity.ILocalizedService;
import io.nzbee.search.ISearchDimensionService;

public interface IBrandService extends ILocalizedService<BrandDTO, BrandEntity>, ISearchDimensionService<BrandDTO> {

	List<BrandDTO> findAll(String locale, Set<String> brandCodes);
	
	List<BrandDTO> findAll(String locale, String categoryCode);
	
	Optional<BrandDTO> findByProductCode(String locale, String productCode);

	List<BrandDTO> findAll(String locale, String currency, String caetgoryCode, Set<String> categoryCodes,
			Set<String> tagCodes, Double maxPrice);

}
