package io.nzbee.view.product.physical.light;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import io.nzbee.view.ports.IPhysicalProductLightPortService;

public class PhysicalProductLightViewServiceImpl implements IPhysicalProductLightViewService {

	@Autowired
	private IPhysicalProductLightPortService physicalProductLightPortService;  
	
	@Override
	public Page<PhysicalProductLightView> findAll(String locale, String currency, String categoryCode,
			Set<String> categoryCodes, Set<String> brandCodes, Set<String> tagCodes, Double maxPrice, String page,
			String size, String sort) {
		return physicalProductLightPortService.findAll(locale, currency, categoryCode, categoryCodes, brandCodes, tagCodes, maxPrice, page, size, sort);
	}


}
