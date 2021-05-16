package io.nzbee.entity.brand.view.facet;

import java.util.List;
import java.util.Optional;
import io.nzbee.entity.StringCollectionWrapper;
import io.nzbee.search.ISearchDimensionService;

public interface IBrandFacetViewService extends ISearchDimensionService<BrandFacetViewDTO> {
	
	List<BrandFacetViewDTO> findAll(String locale, String currency, String caetgoryCode, StringCollectionWrapper categoryCodes,
			StringCollectionWrapper tagCodes, Double maxPrice);
	
	Optional<BrandFacetViewDTO> findByCode(String locale, String rootCategory, String brandCode);

}
