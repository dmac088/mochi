package io.nzbee.entity.product.physical.light;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import io.nzbee.entity.StringCollectionWrapper;

public interface IPhysicalProductLightService {

	public Page<PhysicalProductLightDTO> findAll(String locale, String currency, String rootCategory,
			StringCollectionWrapper categoryCodes, StringCollectionWrapper brandCodes, StringCollectionWrapper tagCodes,
			Double maxPrice, String page, String size, String sort);

	List<PhysicalProductLightDTO> findAll(String locale, String currency, String categoryCode,
			StringCollectionWrapper productCodes);

	Page<PhysicalProductLightDTO> findAll(String locale, String currency, String rootCategoryCode, Pageable pageable,
			String orderby);


}
