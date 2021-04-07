package io.nzbee.entity.product.physical.light;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import io.nzbee.entity.ILocalizedDao;
import io.nzbee.entity.StringCollectionWrapper;
import io.nzbee.entity.product.physical.PhysicalProductEntity;

public interface IPhysicalProductLightDao extends ILocalizedDao<PhysicalProductLightDTO, PhysicalProductEntity> {
	
	Page<PhysicalProductLightDTO> findAll(	String locale, 
								String currency, 
								String rootCategory,
								Pageable pageable,
								String orderby);

	<T> Page<PhysicalProductLightDTO> findAll(String locale, String currency, String categoryCode, StringCollectionWrapper categoryCodes,
			StringCollectionWrapper brandCodes, StringCollectionWrapper tagCodes, Double maxPrice,
			Class<T> cls, String page, String size, String sort);

	Page<PhysicalProductLightDTO> findAll(String locale, String currency, String categoryCode, StringCollectionWrapper categoryCodes,
			StringCollectionWrapper brandCodes, StringCollectionWrapper tagCodes, Double maxPrice, String page,
			String size, String sort);

}
