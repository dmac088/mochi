package io.nzbee.entity.category.product;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import io.nzbee.entity.StringCollectionWrapper;
import io.nzbee.entity.category.CategoryServiceImpl;

@Service
public class CategoryProductServiceImpl implements ICategoryProductService {

	private static final String CACHE_NAME = "productCategoryCache";
	
	@Autowired
	private ICategoryProductDao productCategoryDao;
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME)
	public List<CategoryProductDTO> findAllByProductCode(String locale, String prodctUPC) {
		return productCategoryDao.findAllByProductCode(locale, prodctUPC);
	}
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME, key = "{#locale, #prodctUPC}")
	public Optional<CategoryProductDTO> findPrimaryByProductCode(String locale, String prodctUPC) {
		return productCategoryDao.findPrimaryByProductCode(locale, prodctUPC);
	}

	@Override
	public List<CategoryProductDTO> findAll(String locale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CategoryProductDTO> findAll(String locale, StringCollectionWrapper codes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<CategoryProductDTO> findById(String locale, Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Optional<CategoryProductDTO> findByCode(String locale, String code) {
		return productCategoryDao.findByCode(locale, code);
	}

	@Override
	public Optional<CategoryProductDTO> findByDesc(String locale, String desc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Caching(
			evict = {
				@CacheEvict(cacheNames = CategoryServiceImpl.CACHE_NAME + "Other", allEntries = true),
				@CacheEvict(cacheNames = CategoryServiceImpl.CACHE_NAME, key="#category.categoryCode"),
				@CacheEvict(cacheNames = CategoryServiceImpl.CACHE_NAME, key="{#category.locale, #category.categoryId}"),
				@CacheEvict(cacheNames = CategoryServiceImpl.CACHE_NAME, key="{#category.locale, #category.categoryCode}")
			})
	public void save(CategoryProductEntity category) {
		productCategoryDao.save(category);
	}

	@Override
	public void update(CategoryProductEntity t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(CategoryProductEntity t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<CategoryProductEntity> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<CategoryProductEntity> findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<CategoryProductEntity> findByCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CategoryProductEntity> findAll(Set<String> codes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CategoryProductDTO> findAll(String locale, String currency, StringCollectionWrapper codes) {
		// TODO Auto-generated method stub
		return null;
	}

}
