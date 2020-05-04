package io.nzbee.entity.brand;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import io.nzbee.entity.ILocalizedService;
import io.nzbee.search.ISearchDimensionService;

public interface IBrandService extends ILocalizedService<Brand>, ISearchDimensionService<Brand> {

	List<Brand> findAll(String locale, String currency, Set<String> brandCodes);
	
	List<Brand> findAll(String locale, String currency, Set<String> categoryCodes, Set<String> tagCodes);

	List<Brand> findAll(String locale, String currency, String categoryCode);
	
	Optional<Brand> findByProductCode(String locale, String currency, String productCode);

}
