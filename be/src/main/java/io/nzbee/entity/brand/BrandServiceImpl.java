package io.nzbee.entity.brand;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

@Service
public class BrandServiceImpl implements IBrandService {

	public static final String CACHE_NAME = "brandCache";
	
	@Autowired 
	private IBrandRepository brandRepository;
	
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
	public Optional<BrandEntity> findByCode(String brandCode) {
		return brandRepository.findByBrandCode(brandCode);
	}

	@Override
	public void update(BrandEntity t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(BrandEntity t) {
		// TODO Auto-generated method stub
		
	}

}