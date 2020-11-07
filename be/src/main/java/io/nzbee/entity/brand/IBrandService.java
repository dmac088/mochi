package io.nzbee.entity.brand;

import java.util.Optional;
import java.util.Set;
import io.nzbee.entity.ILocalizedService;
import io.nzbee.search.ISearchDimensionService;

public interface IBrandService extends ILocalizedService<BrandDTO, BrandEntity>, ISearchDimensionService<BrandDTO> {

	Set<BrandDTO> findAll(String locale, Set<String> brandCodes);
	
	Set<BrandDTO> findAll(String locale, String categoryCode);
	
	Optional<BrandDTO> findByProductCode(String locale, String productCode);

	Set<BrandDTO> findAll(String locale, String currency, String caetgoryCode, Set<String> categoryCodes,
			Set<String> tagCodes, Double maxPrice);

}
