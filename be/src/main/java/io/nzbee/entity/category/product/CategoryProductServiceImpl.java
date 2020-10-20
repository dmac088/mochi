package io.nzbee.entity.category.product;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import io.nzbee.entity.category.CategoryServiceImpl;

@Service
public class CategoryProductServiceImpl implements ICategoryProductService {

	private static final String CACHE_NAME = "productCategoryCache";
	
	@Autowired
	private ICategoryProductDao productCategoryDao;
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME)
	public List<CategoryProduct> findAllByProductCode(String locale, String prodctUPC) {
		return productCategoryDao.findAllByProductCode(locale, prodctUPC);
	}
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME, key = "{#locale, #prodctUPC}")
	public Optional<CategoryProduct> findPrimaryByProductCode(String locale, String prodctUPC) {
		return productCategoryDao.findPrimaryByProductCode(locale, prodctUPC);
	}

	@Override
	public Set<CategoryProduct> findAll(String locale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<CategoryProduct> findAll(String locale, Set<String> codes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<CategoryProduct> findById(String locale, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<CategoryProduct> findByCode(String locale, String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<CategoryProduct> findByDesc(String locale, String desc) {
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
	public void save(CategoryProduct category) {
		productCategoryDao.save(category);
	}
	
	@Override
	public CategoryProduct merge(CategoryProduct category) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(CategoryProduct t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(CategoryProduct t) {
		// TODO Auto-generated method stub
		
	}

}