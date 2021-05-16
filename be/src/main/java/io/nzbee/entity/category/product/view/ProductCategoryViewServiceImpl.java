package io.nzbee.entity.category.product.view;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import io.nzbee.entity.StringCollectionWrapper;

@Service
public class ProductCategoryViewServiceImpl implements IProductCategoryViewService {

	public static final String CACHE_NAME = "categoryCache";
	
	@Autowired
	private IProductCategoryViewDao productCategoryDao;
	
	@Override
	public List<ProductCategoryViewDTO> findAll(String locale, String rootCategory) {
		return productCategoryDao.findAll(locale, rootCategory);
	}
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME + "Other", key="#locale + \", \" + #currency + \", \" + #categoryCode + \", \" + #categoryCodes.getCacheKey() + \", \" + #brandCodes.getCacheKey() + \", \" + #tagCodes.getCacheKey() + \", \" + #maxPrice")
	public List<ProductCategoryViewDTO> findAll(	String locale, 
													String currency, String categoryCode,
													StringCollectionWrapper categoryCodes, 
													StringCollectionWrapper brandCodes, 
													StringCollectionWrapper tagCodes, 
													Double maxPrice) {
		return productCategoryDao.findAll(locale, currency, categoryCode, categoryCodes, brandCodes, tagCodes, maxPrice);
	}
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME + "Other", key="#locale + \", \" + #currency + \", \" + #categoryCode + \", \" + #categoryCodes.getCacheKey() + \", \" + #brandCodes.getCacheKey() + \", \" + #tagCodes.getCacheKey()")
	public Double getMaxPrice(String locale, String currency, String categoryCode, StringCollectionWrapper categoryCodes, StringCollectionWrapper brandCodes, StringCollectionWrapper tagCodes) {
		return productCategoryDao.getMaxPrice(locale, currency, categoryCode, categoryCodes, brandCodes, tagCodes);
	}
	
	@Override
	public void save(ProductCategoryViewDTO t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(ProductCategoryViewDTO t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(ProductCategoryViewDTO t) {
		// TODO Auto-generated method stub
		
	}



}
