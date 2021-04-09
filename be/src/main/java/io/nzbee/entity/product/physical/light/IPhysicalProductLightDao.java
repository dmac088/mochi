package io.nzbee.entity.product.physical.light;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import io.nzbee.entity.StringCollectionWrapper;

public interface IPhysicalProductLightDao {
	
	Page<PhysicalProductLightDTO> findAll(	String locale, 
								String currency, 
								String rootCategory,
								Pageable pageable,
								String orderby);

	Page<PhysicalProductLightDTO> findAll(String locale, String currency, String categoryCode, StringCollectionWrapper categoryCodes,
			StringCollectionWrapper brandCodes, StringCollectionWrapper tagCodes, Double maxPrice, String page,
			String size, String sort);

}
