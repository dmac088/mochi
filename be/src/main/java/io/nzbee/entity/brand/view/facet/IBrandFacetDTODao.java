package io.nzbee.entity.brand.view.facet;

import java.util.List;
import java.util.Optional;
import io.nzbee.entity.IDao;
import io.nzbee.entity.StringCollectionWrapper;

public interface IBrandFacetDTODao extends IDao<BrandFacetDTO> {

	List<BrandFacetDTO> findAll(String locale, String currency, String caetgoryCode, StringCollectionWrapper categoryCodes,
			StringCollectionWrapper tagCodes, Double maxPrice);

	List<BrandFacetDTO> findAll(String locale, String rootCategory, StringCollectionWrapper brandCodes);

	Optional<BrandFacetDTO> findByCode(String locale, String rootCategory, String code);

}
