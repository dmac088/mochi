package io.nzbee.entity.category.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import io.nzbee.entity.category.CategoryServiceImpl;

@Service
public class CategoryProductServiceImpl implements ICategoryProductService {

	
	@Autowired
	private ICategoryProductDao productCategoryDao;

	@Override
	@Caching(
			evict = {
				@CacheEvict(cacheNames = CategoryServiceImpl.CACHE_NAME + "Other", allEntries = true),
				@CacheEvict(cacheNames = CategoryServiceImpl.CACHE_NAME, key="#category.categoryCode"),
				@CacheEvict(cacheNames = CategoryServiceImpl.CACHE_NAME, key="#category.locale + \", \" + #category.categoryId.toString()"),
				@CacheEvict(cacheNames = CategoryServiceImpl.CACHE_NAME, key="#category.locale + \", \" + #category.categoryCode")
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

}
