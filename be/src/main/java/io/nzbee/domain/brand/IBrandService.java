package io.nzbee.domain.brand;

import java.util.Set;
import io.nzbee.domain.ILocalizedService;
import io.nzbee.search.dto.facet.IFacet;

public interface IBrandService extends ILocalizedService<Brand> {
	
	Set<Brand> findAll(String locale, String currency, String category);
	
	Brand findByProductCode(String locale, String currency, String productCode);

	Set<Brand> findAll(String locale, String currency, String categoryCode, Set<IFacet> selectedFacets);
	
}
