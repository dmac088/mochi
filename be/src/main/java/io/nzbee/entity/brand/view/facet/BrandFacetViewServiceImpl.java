package io.nzbee.entity.brand.view.facet;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import io.nzbee.entity.StringCollectionWrapper;

@Service(value="brandEntityService")
public class BrandFacetViewServiceImpl implements IBrandFacetViewService {

	public static final String CACHE_NAME = "brandCache";
	
	@Autowired
	private IBrandFacetViewDao brandDao;
	
	@Override
	public String tokenToCode(String token) {
		return "product.brand.brandToken";
	}

	@Override
	@Cacheable(cacheNames = CACHE_NAME, key = "#locale + \", \" + #rootCategory + \", \" + #brandCodes.getCacheKey()")
	public List<BrandFacetViewDTO> findAll(String locale, String currency, String rootCategory,
			StringCollectionWrapper brandCodes) {
		return brandDao.findAll(locale, rootCategory, brandCodes);
	}

	@Override
	@Cacheable(cacheNames = CACHE_NAME + "Other", key="#locale + \", \" + #currency + \", \" + #categoryCode + \", \" + #categoryCodes.getCacheKey() + \", \" + #tagCodes.getCacheKey() + \", \" + ((#maxPrice == null) ? '' : #maxPrice.toString())")
	public List<BrandFacetViewDTO> findAll(String locale, String currency, String categoryCode,
			StringCollectionWrapper categoryCodes, StringCollectionWrapper tagCodes, Double maxPrice) {
		return brandDao.findAll(locale, currency, categoryCode, categoryCodes, tagCodes, maxPrice);
	}
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME, key = "#locale + \", \" + #rootCategory + \", \" + #brandCode")
	public Optional<BrandFacetViewDTO> findByCode(String locale, String rootCategory, String brandCode) {
		return brandDao.findByCode(locale, rootCategory, brandCode);
	}


}
