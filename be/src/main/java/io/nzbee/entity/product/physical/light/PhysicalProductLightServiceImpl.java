package io.nzbee.entity.product.physical.light;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import io.nzbee.entity.StringCollectionWrapper;

@Service
public class PhysicalProductLightServiceImpl implements IPhysicalProductLightService {

	@Autowired
	private IPhysicalProductLightRepository productRepository;
	
	@Override
	public List<PhysicalProductLightDTO> findAll(String locale, 
												 String currency,
												 String categoryCode,
												 StringCollectionWrapper productCodes) {
		return productRepository.findAll(locale, currency, categoryCode, productCodes.getCodes());
	}

	@Override
	public Page<PhysicalProductLightDTO> findAll(String locale, String currency, String rootCategory,
			StringCollectionWrapper categoryCodes, StringCollectionWrapper brandCodes, StringCollectionWrapper tagCodes,
			Double maxPrice, String page, String size, String sort) {
		// TODO Auto-generated method stub
		return null;
	}

}
