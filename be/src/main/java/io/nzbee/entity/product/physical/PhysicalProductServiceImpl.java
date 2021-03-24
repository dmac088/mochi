package io.nzbee.entity.product.physical;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import io.nzbee.entity.StringCollectionWrapper;
import io.nzbee.entity.product.IProductDao;
import io.nzbee.entity.product.ProductServiceImpl;

@Service
public class PhysicalProductServiceImpl implements IPhysicalProductService {

	@Autowired
	private IProductDao productDAO;

	@Override
	@Cacheable(cacheNames = ProductServiceImpl.CACHE_NAME + "Other", key="#locale + \", \" + #currency + \", \" + #rootCategory + \", \" + #categoryCodes.getCacheKey() + \", \" + #brandCodes.getCacheKey() + \", \" + #tagCodes.getCacheKey() + \", \" + ((#maxPrice == null) ? '' : #maxPrice.toString()) + \", \" + #page.toString() + \", \" + #size.toString() + \", \" + #sort.toString()")
	public Page<PhysicalProductDTO> findAll(String locale, String currency, String rootCategory,
			StringCollectionWrapper categoryCodes, StringCollectionWrapper brandCodes, StringCollectionWrapper tagCodes,
			Double maxPrice, String page, String size, String sort) {
		return productDAO.findAll(
				  locale,
		 		  currency,
		 		  rootCategory,
		 		  categoryCodes,
		 		  brandCodes, 
		 		  tagCodes,
		 		  maxPrice,
		 		  PhysicalProductEntity.class,
		 		  page,
		 		  size,
		 		  sort
		  	).map(p -> (PhysicalProductDTO) p);
	}
	
	
	
	

}
