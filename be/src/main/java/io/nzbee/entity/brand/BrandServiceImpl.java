package io.nzbee.entity.brand;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import io.nzbee.Constants;
import io.nzbee.entity.StringCollectionWrapper;
import io.nzbee.search.IFacetService;

@Service(value="brandEntityService")
public class BrandServiceImpl implements IBrandService, IFacetService {

	public static final String CACHE_NAME = "brandCache";
	
	@Autowired
	private IBrandDao brandDao; 
	
	@Autowired 
	private IBrandRepository brandRepository;

	@Override
	public List<BrandEntity> findAll() {
		return brandRepository.findAll();
	}

	@Override
	public Optional<BrandEntity> findById(Long id) {
		return brandRepository.findById(id);
	}	
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME, key = "#locale + \", \" + #id.toString()")
	public Optional<BrandDTO> findById(String locale, Long id) {
		return brandDao.findById(locale, id);
	}
	
	@Override
	public Optional<BrandEntity> findByCode(String code) {
		return brandDao.findByCode(code);
	}

	@Override
	@Cacheable(cacheNames = CACHE_NAME, key = "#locale + \", \" + #rootCategory + \", \" + #code")
	public Optional<BrandDTO> findByCode(String locale, String rootCategory, String code) {
		return brandDao.findByCode(locale, rootCategory,code);
	}
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME + "Other", key = "#locale + \", \" + #rootCategory + \", \" + #desc")
	public Optional<BrandDTO> findByDesc(String locale, String rootCategory, String desc) {
		return brandDao.findByDesc(locale, rootCategory, desc);
	}
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME + "Other")
	public List<BrandDTO> findAll(String locale) {
		return brandRepository.findAll(locale);
	}
	
	@Override
	public List<BrandDTO> findAllByProductType(String locale, Class<?> cls) {
		return brandRepository.findAllByProductType(locale, cls.getSimpleName());
	}
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME + "Other", key="#locale + \", \" + #currency + \", \" + #categoryCode + \", \" + #categoryCodes.getCacheKey() + \", \" + #tagCodes.getCacheKey() + \", \" + ((#maxPrice == null) ? '' : #maxPrice.toString())")
	public List<BrandDTO> findAll(String locale, String currency, String categoryCode,  StringCollectionWrapper categoryCodes, StringCollectionWrapper tagCodes, Double maxPrice) {
		return brandDao.findAll(locale, currency, categoryCode, categoryCodes, tagCodes, maxPrice);
	}
	
	@Override
	public List<BrandDTO> findAll(String locale, String rootCategory, String categoryCode) {
		return brandDao.findAllByCategory(locale, rootCategory, categoryCode);
	}
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME, key = "#locale + \", \" + #rootCategory + \", \" + #brandCodes.getCacheKey()")
	public List<BrandDTO> findAll(String locale, String currency, String rootCategory, StringCollectionWrapper brandCodes) {
		return brandDao.findAll(locale, rootCategory, brandCodes);
	}

	@Override
	@Caching(
			evict = {
				@CacheEvict(cacheNames = CACHE_NAME, key="#brand.brandId.toString()"),
				@CacheEvict(cacheNames = CACHE_NAME, key="#brand.brandCode"),
				@CacheEvict(cacheNames = CACHE_NAME, key="#brand.locale + \", \" + #brand.brandId.toString()"),
				@CacheEvict(cacheNames = CACHE_NAME, key="#brand.locale + \", \" + #brand.brandCode"),
				@CacheEvict(cacheNames = CACHE_NAME + "Other", 			allEntries = true)
			})
	public void save(BrandEntity brand) {
		brandRepository.save(brand);
	}

	@Override
	public void update(BrandEntity t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(BrandEntity t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getFacetField() {
		return "product.brand.brandToken";
	}

	@Override
	public String getFacetCategory() {
		return "brand";
	}

	@Override
	public String tokenToCode(String token) {
		return token;
	}

	@Override
	public List<BrandEntity> findAll(Set<String> codes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BrandDTO> findAll(String locale, StringCollectionWrapper codes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<BrandDTO> findByCode(String locale, String code) {
		return this.findByCode(locale, Constants.defaultProductRootCategoryCode, code);
	}

	@Override
	public Optional<BrandDTO> findByDesc(String locale, String desc) {
		return this.findByDesc(locale, Constants.defaultProductRootCategoryCode, desc);
	}

}