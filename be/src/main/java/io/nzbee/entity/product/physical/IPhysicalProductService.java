package io.nzbee.entity.product.physical;

import org.springframework.data.domain.Page;
import io.nzbee.entity.StringCollectionWrapper;

public interface IPhysicalProductService {

	public Page<PhysicalProductDTO> findAll(String locale, String currency, String rootCategory,
			StringCollectionWrapper categoryCodes, StringCollectionWrapper brandCodes, StringCollectionWrapper tagCodes,
			Double maxPrice, String page, String size, String sort);
	
	
	
}
