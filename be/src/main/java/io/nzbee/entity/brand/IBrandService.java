package io.nzbee.entity.brand;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import io.nzbee.entity.ILocalizedService;
import io.nzbee.search.ISearchDimensionService;

public interface IBrandService extends ILocalizedService<Brand>, ISearchDimensionService<Brand> {

	List<Brand> findAll(String locale, Set<String> brandCodes);
	
	List<Brand> findAll(String locale, String categoryCode);
	
	Optional<Brand> findByProductCode(String locale, String productCode);

	List<Brand> findAll(String locale, String currency, String caetgoryCode, Set<String> categoryCodes,
			Set<String> tagCodes, Double maxPrice);


}
