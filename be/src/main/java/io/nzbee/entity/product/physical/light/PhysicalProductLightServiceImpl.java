package io.nzbee.entity.product.physical.light;

import java.util.List;

import org.apache.tomcat.util.buf.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import io.nzbee.entity.StringCollectionWrapper;

@Service
public class PhysicalProductLightServiceImpl implements IPhysicalProductLightService {

	private final Logger LOGGER = LoggerFactory.getLogger(PhysicalProductLightServiceImpl.class);
	
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
		LOGGER.debug("call PhysicalProductLightServiceImpl.findAll with parameters : {}, {}, {}, {}", 
						locale, 
						currency,
						rootCategory, 
						StringUtils.join(productCodes.getCodes()));
		return productRepository.findAll(locale, currency, rootCategory, productCodes.getCodes());
	}
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME, key="#locale + \", \" + #currency + \", \" + #rootCategory + \", \" + #pageable.getPageSize() + \", \" + #pageable.getOffset() + \", \" + #orderby")
	public Page<PhysicalProductLightDTO> findAll(String 	locale, 
												 String 	currency,
												 String 	rootCategory,
												 Pageable 	pageable,
												 String 	orderby) {
		LOGGER.debug("call PhysicalProductLightServiceImpl.findAll with parameters : {}, {}, {}, {}", 
						locale,
						currency,
						rootCategory,
						pageable,
						orderby);
		return productDao.findAll(locale, currency, rootCategory, pageable, orderby);
	}
	
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME, key="#locale + \", \" + #currency + \", \" + #rootCategory + \", \" + #categoryCodes.getCacheKey() + \", \" + #brandCodes.getCacheKey() + \", \" + #tagCodes.getCacheKey() + \", \" + ((#maxPrice == null) ? '' : #maxPrice.toString()) + \", \" + #page + \", \" + #size + \", \" + #orderby")
	public Page<PhysicalProductLightDTO> findAll(	String locale, 
													String currency, 
													String rootCategory,
													StringCollectionWrapper categoryCodes, 
													StringCollectionWrapper brandCodes, 
													StringCollectionWrapper tagCodes,
													Double maxPrice, 
													String page, 
													String size, 
													String orderby) {
		LOGGER.debug("call PhysicalProductLightServiceImpl.findAll with parameters : {}, {}, {}, {}, {}, {}, {}, {}, {}, {}",
				locale,
				currency,
				rootCategory,
				StringUtils.join(categoryCodes.getCodes()),
				StringUtils.join(brandCodes.getCodes()),
				StringUtils.join(tagCodes.getCodes()),
				maxPrice,
				page,
				size,
				orderby);
		return productDao.findAll(locale, currency, rootCategory, categoryCodes, brandCodes, tagCodes, maxPrice, page, size, orderby);
	}

}
