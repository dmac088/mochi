package io.nzbee.entity.brand.view.facet;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import io.nzbee.entity.StringCollectionWrapper;
import io.nzbee.search.IFacetService;

@Service(value = "brandFacetService")
public class BrandFacetDTOServiceImpl implements IBrandFacetDTOService, IFacetService {

	public static final String CACHE_NAME = "brandCache";
	
	@Override
	public String getFacetField() {
		return "product.brand.brandToken";
	}

	@Override
	public String getFacetCategory() {
		return "brand";
	}
	
	@Autowired
	private IBrandFacetDTODao brandDao;
	
	@Override
	public String tokenToCode(String token) {
		return token;
	}

	@Override
	@Cacheable(cacheNames = CACHE_NAME, key = "#locale + \", \" + #rootCategory + \", \" + #brandCodes.getCacheKey()")
	public List<BrandFacetDTO> findAll(String locale, String currency, String rootCategory,
			StringCollectionWrapper brandCodes) {
		return brandDao.findAll(locale, rootCategory, brandCodes);
	}

	@Override
	@Cacheable(cacheNames = CACHE_NAME + "Other", key="#locale + \", \" + #currency + \", \" + #categoryCode + \", \" + #categoryCodes.getCacheKey() + \", \" + #tagCodes.getCacheKey() + \", \" + ((#maxPrice == null) ? '' : #maxPrice.toString())")
	public List<BrandFacetDTO> findAll(String locale, String currency, String categoryCode,
			StringCollectionWrapper categoryCodes, StringCollectionWrapper tagCodes, Double maxPrice) {
		return brandDao.findAll(locale, currency, categoryCode, categoryCodes, tagCodes, maxPrice);
	}
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME, key = "#locale + \", \" + #rootCategory + \", \" + #brandCode")
	public Optional<BrandFacetDTO> findByCode(String locale, String rootCategory, String brandCode) {
		return brandDao.findByCode(locale, rootCategory, brandCode);
	}

	@Override
	public void save(BrandFacetDTO t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(BrandFacetDTO t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(BrandFacetDTO t) {
		// TODO Auto-generated method stub
		
	}



}
