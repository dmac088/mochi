package io.nzbee.entity.brand;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import io.nzbee.entity.StringCollectionWrapper;
import io.nzbee.search.IFacetService;

@Service(value="brandEntityService")
public class BrandServiceImpl implements IBrandService, IFacetService {

	private static final String CACHE_NAME = "brandCache";
	
	@Autowired
	private IBrandDao brandDao; 
	
	@Autowired
	private IBrandRepository brandRepository;

	//Entity fetch
	@Override
	public List<BrandEntity> findAll() {
		return brandRepository.findAll();
	}

	@Override
	@Cacheable(cacheNames = CACHE_NAME, key = "{#id}")
	public Optional<BrandEntity> findById(Long id) {
		return brandRepository.findById(id);
	}	
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME, key = "{#locale, #id}")
	public Optional<BrandDTO> findById(String locale, Long id) {
		return brandDao.findById(locale, id);
	}
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME, key = "#code")
	public Optional<BrandEntity> findByCode(String code) {
		return brandDao.findByCode(code);
	}

	//DTO Fetch
	@Override
	@Cacheable(cacheNames = CACHE_NAME, key = "{#locale, #code}")
	public Optional<BrandDTO> findByCode(String locale, String code) {
		return brandDao.findByCode(locale, code);
	}
	
	@Override
	public Optional<BrandDTO> findByDesc(String locale, String desc) {
		return brandDao.findByDesc(locale, desc);
	}
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME + "Other")
	public List<BrandDTO> findAll(String locale) {
		return brandDao.findAll(locale);
	}
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME + "Other")
	public List<BrandDTO> findAll(String locale, Set<String> codes) {
		return brandDao.findAll(locale, codes);
	}

	@Override
	@Cacheable(cacheNames = CACHE_NAME + "Other", key="{#locale, #currency, #categoryCode, #categoryCodes.getCacheKey(), #tagCodes.getCacheKey(), #maxPrice}")
	public List<BrandDTO> findAll(String locale, String currency, String categoryCode,  StringCollectionWrapper categoryCodes, StringCollectionWrapper tagCodes, Double maxPrice) {
		return brandDao.findAll(locale, currency, categoryCode, categoryCodes, tagCodes, maxPrice);
	}
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME + "Other")
	public List<BrandDTO> findAll(String locale, String categoryCode) {
		return brandDao.findAllByCategory(locale, categoryCode);
	}
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME + "ByProductCode", key = "{#locale, #productCode}")
	public Optional<BrandDTO> findByProductCode(String locale, String productCode) {
		return brandDao.findByProductCode(locale, productCode);
	}
	
	@Override
	public List<BrandDTO> findAll(String locale, String currency, Set<String> brandCodes) {
		return brandDao.findAll(locale, brandCodes);
	}

	@Override
	@Caching(
			evict = {
				@CacheEvict(cacheNames = CACHE_NAME, key="{#brand.brandId}"),
				@CacheEvict(cacheNames = CACHE_NAME, key="{#brand.brandCode}"),
				@CacheEvict(cacheNames = CACHE_NAME, key="{#brand.locale, #brand.brandId}"),
				@CacheEvict(cacheNames = CACHE_NAME, key="{#brand.locale, #brand.brandCode}"),
				@CacheEvict(cacheNames = CACHE_NAME + "Other", 			allEntries = true),
				@CacheEvict(cacheNames = CACHE_NAME + "ByProductCode", 	allEntries = true),
			})
	public void save(BrandEntity brand) {
		brandDao.save(brand);
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

}