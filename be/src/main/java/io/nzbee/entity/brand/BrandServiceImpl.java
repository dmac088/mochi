package io.nzbee.entity.brand;

import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import io.nzbee.search.IFacetService;

@Service(value="brandEntityService")
public class BrandServiceImpl implements IBrandService, IFacetService {

	private static final String CACHE_NAME = "brandCache";
	
	@Autowired
	private IBrandDao brandDao; 

	@Override
	@Cacheable(cacheNames = CACHE_NAME, key = "{#locale, #id}")
	public Optional<Brand> findById(String locale, long Id) {
		return brandDao.findById(locale, Id);
	}
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME, key = "{#code}")
	public Optional<Brand> findByCode(String code) {
		return brandDao.findByCode(code);
	}

	@Override
	@Cacheable(cacheNames = CACHE_NAME, key = "{#locale, #code}")
	public Optional<Brand> findByCode(String locale, String code) {
		return brandDao.findByCode(locale, code);
	}
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME, key = "{#locale, #desc}")
	public Optional<Brand> findByDesc(String locale, String desc) {
		return brandDao.findByDesc(locale, desc);
	}
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME + "Other")
	public Set<Brand> findAll(String locale) {
		return brandDao.findAll(locale);
	}

	@Override
	@Cacheable(cacheNames = CACHE_NAME + "Other")
	public Set<Brand> findAll(String locale, String currency, String categoryCode,  Set<String> categoryCodes, Set<String> tagCodes, Double maxPrice) {
		return brandDao.findAll(locale, currency, categoryCode, categoryCodes, tagCodes, maxPrice);
	}
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME + "Other")
	public Set<Brand> findAll(String locale, Set<String> codes) {
		return brandDao.findAll(locale, codes);
	}

	@Override
	@Cacheable(cacheNames = CACHE_NAME + "Other")
	public Set<Brand> findAll(String lcl, String currency, Set<String> codes) {
		return brandDao.findAll(lcl, codes);
	}
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME + "Other")
	public Set<Brand> findAll(String locale, String categoryCode) {
		return brandDao.findAllByCategory(locale, categoryCode);
	}
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME + "ByProductCode", key = "{#locale, #productCode}")
	public Optional<Brand> findByProductCode(String locale, String productCode) {
		return brandDao.findByProductCode(locale, productCode);
	}

	@Override
	@Caching(evict = {
			  @CacheEvict(cacheNames = CACHE_NAME, key="{#brand.brandCode}"),
			  @CacheEvict(cacheNames = CACHE_NAME, key="{#brand.locale, #brand.brandId}"),
			  @CacheEvict(cacheNames = CACHE_NAME, key="{#brand.locale, #brand.brandCode}"),
			  @CacheEvict(cacheNames = CACHE_NAME, key="{#brand.locale, #brand.brandDesc}"),
			  @CacheEvict(cacheNames = CACHE_NAME + "Other", 			allEntries = true),
			  @CacheEvict(cacheNames = CACHE_NAME + "ByProductCode", 	allEntries = true)
			})
	public void save(Brand brand) {
		brandDao.save(brand);
	}

	@Override
	public void update(Brand t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Brand t) {
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


}