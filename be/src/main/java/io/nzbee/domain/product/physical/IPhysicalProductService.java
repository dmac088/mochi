package io.nzbee.domain.product.physical;

import java.util.Set;
import org.springframework.data.domain.Page;

public interface IPhysicalProductService {


	Page<PhysicalProduct> findAll(	String locale, 
									String currency, 
									String categoryCode,
									Set<String> categoryCodes,
									Set<String> brandCodes,
									Set<String> tagCodes,
									Double maxPrice,
									String page, 
									String size, 
									String sort);

	

}
