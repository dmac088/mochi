package io.nzbee.entity.product.physical.light;

import java.util.List;
import io.nzbee.entity.StringCollectionWrapper;

public interface IPhysicalProductLightService {

	List<PhysicalProductLightDTO> findAll(String locale, String currency, String categoryCode,
			StringCollectionWrapper productCodes);


}
