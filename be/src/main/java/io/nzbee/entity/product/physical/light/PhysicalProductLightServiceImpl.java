package io.nzbee.entity.product.physical.light;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import io.nzbee.entity.StringCollectionWrapper;

@Service
public class PhysicalProductLightServiceImpl implements IPhysicalProductLightService {

	@Autowired
	private IPhysicalProductLightRepository productRepository;
	
	@Autowired
	private IPhysicalProductLightDao productDao;
	
	@Override
	public List<PhysicalProductLightDTO> findAll(String locale, 
												 String currency,
												 String categoryCode,
												 StringCollectionWrapper productCodes) {
		
		return productRepository.findAll(locale, currency, categoryCode, productCodes.getCodes());
	}
	
	@Override
	public Page<PhysicalProductLightDTO> findAll(String 	locale, 
												 String 	currency,
												 String 	rootCategoryCode,
												 Pageable 	pageable,
												 String 	orderby) {
		
		return productDao.findAll(locale, currency, rootCategoryCode, pageable, orderby);
	}
	
	

	@Override
	public Page<PhysicalProductLightDTO> findAll(	String locale, 
													String currency, 
													String rootCategory,
													StringCollectionWrapper categoryCodes, 
													StringCollectionWrapper brandCodes, 
													StringCollectionWrapper tagCodes,
													Double maxPrice, 
													String page, 
													String size, 
													String sort) {

		return productDao.findAll(locale, currency, rootCategory, categoryCodes, brandCodes, tagCodes, maxPrice, page, size, sort);
	}

}
