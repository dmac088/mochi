package io.nzbee.entity.category.product.view.facet;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import io.nzbee.entity.StringCollectionWrapper;
import io.nzbee.search.IFacetService;

@Service
public class ProductCategoryFacetViewServiceImpl implements IProductCategoryFacetViewService, IFacetService {

	public static final String CACHE_NAME = "categoryCache";
	
	@Autowired
	private IProductCategoryFacetViewDao productCategoryDao;


	@Override
	public String getFacetField() {
		return "product.categories.categoryToken";
	}

	@Override
	public String getFacetCategory() {
		return "category";
	}
	
	@Override
	public List<ProductCategoryFacetViewDTO> findAll(String locale, String rootCategory) {
		return productCategoryDao.findAll(locale, rootCategory);
	}
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME + "Other", key="#locale + \", \" + #currency + \", \" + #categoryCode + \", \" + #categoryCodes.getCacheKey() + \", \" + #brandCodes.getCacheKey() + \", \" + #tagCodes.getCacheKey() + \", \" + #maxPrice")
	public List<ProductCategoryFacetViewDTO> findAll(	String locale, 
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
	@Cacheable(cacheNames = CACHE_NAME + "Other", key="#locale + \", \" + #rootCategory + \", \" + #codes.getCacheKey()")
	public List<ProductCategoryFacetViewDTO> findAll(String locale, String currency, String rootCategory, StringCollectionWrapper codes) {
		return productCategoryDao.findAll(locale, codes);
	}

	@Override
	public String tokenToCode(String token) {
		return token.substring(token.lastIndexOf('/')+1,token.length());
	}
	
	@Override
	public void save(ProductCategoryFacetViewDTO t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(ProductCategoryFacetViewDTO t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(ProductCategoryFacetViewDTO t) {
		// TODO Auto-generated method stub
		
	}




}
