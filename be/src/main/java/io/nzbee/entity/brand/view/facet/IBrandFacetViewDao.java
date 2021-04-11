package io.nzbee.entity.brand.view.facet;

import java.util.List;
import java.util.Optional;
import io.nzbee.entity.IDao;
import io.nzbee.entity.StringCollectionWrapper;

public interface IBrandFacetViewDao extends IDao<BrandFacetViewDTO> {

	List<BrandFacetViewDTO> findAll(String locale, String currency, String caetgoryCode, StringCollectionWrapper categoryCodes,
			StringCollectionWrapper tagCodes, Double maxPrice);

	List<BrandFacetViewDTO> findAll(String locale, String rootCategory, StringCollectionWrapper brandCodes);

	Optional<BrandFacetViewDTO> findByCode(String locale, String rootCategory, String code);

}
