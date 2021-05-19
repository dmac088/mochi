package io.nzbee.entity.brand.view.facet;

import java.util.List;
import java.util.Optional;
import io.nzbee.entity.StringCollectionWrapper;
import io.nzbee.search.ISearchDimensionService;

public interface IBrandFacetDTOService extends ISearchDimensionService<BrandFacetDTO> {
	
	List<BrandFacetDTO> findAll(String locale, String currency, String caetgoryCode, StringCollectionWrapper categoryCodes,
			StringCollectionWrapper tagCodes, Double maxPrice);
	
	Optional<BrandFacetDTO> findByCode(String locale, String rootCategory, String brandCode);

}
