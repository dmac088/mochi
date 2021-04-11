package io.nzbee.entity.brand.view;

import java.util.List;
import io.nzbee.entity.IDao;
import io.nzbee.entity.StringCollectionWrapper;

public interface IBrandFacetViewDao extends IDao<BrandFacetViewDTO> {

	List<BrandFacetViewDTO> findAll(String locale, String currency, String caetgoryCode, StringCollectionWrapper categoryCodes,
			StringCollectionWrapper tagCodes, Double maxPrice);
	
}
