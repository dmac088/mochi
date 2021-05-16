package io.nzbee.entity.product.physical.light;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import io.nzbee.entity.StringCollectionWrapper;

@Service
public class PhysicalProductLightServiceImpl implements IPhysicalProductLightService {

	public static final String CACHE_NAME = "productLightCache";
	
	@Autowired
	private IPhysicalProductLightRepository productRepository;
	
	@Autowired
	private IPhysicalProductLightDao productDao;
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME, key="#locale + \", \" + #currency + \", \" + #rootCategory + \", \" + #productCodes.getCacheKey()")
	public List<PhysicalProductLightDTO> findAll(String locale, 
												 String currency,
												 String rootCategory,
												 StringCollectionWrapper productCodes) {
		
		return productRepository.findAll(locale, currency, rootCategory, productCodes.getCodes());
	}
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME, key="#locale + \", \" + #currency + \", \" + #rootCategory + \", \" + #pageable.getPageSize() + \", \" + #pageable.getOffset() + \", \" + #orderby ")
	public Page<PhysicalProductLightDTO> findAll(String 	locale, 
												 String 	currency,
												 String 	rootCategory,
												 Pageable 	pageable,
												 String 	orderby) {
		
		return productDao.findAll(locale, currency, rootCategory, pageable, orderby);
	}
	
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME, key="#locale + \", \" + #currency + \", \" + #rootCategory + \", \" + #categoryCodes.getCacheKey() + \", \" + #brandCodes.getCacheKey() + \", \" + #tagCodes.getCacheKey() + \", \" + ((#maxPrice == null) ? '' : #maxPrice.toString()) + \", \" + #page + \", \" + #size + \", \" + #sort")
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
