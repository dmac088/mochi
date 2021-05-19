package io.nzbee.entity.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import io.nzbee.search.IFacetService;

@Service(value = "categoryEntityService")
public class CategoryServiceImpl implements ICategoryService, IFacetService {
	
	public static final String CACHE_NAME = "categoryCache";
	
	@Autowired
	@Qualifier(value = "categoryEntityPostgresDao")
	private ICategoryDao categoryDAO;
	
	@Override
	@Caching(evict = {
			@CacheEvict(cacheNames = CACHE_NAME + "Other", 	allEntries = true),
			@CacheEvict(cacheNames = CACHE_NAME, key="#category.categoryId.toString()"),
			@CacheEvict(cacheNames = CACHE_NAME, key="#category.categoryCode"),
			@CacheEvict(cacheNames = CACHE_NAME, key="#category.locale + \", \" + #category.categoryId.toString()"),
			@CacheEvict(cacheNames = CACHE_NAME, key="#category.locale + \", \" + #category.categoryCode")
	})
	


	public String getFacetField() {
		return "product.categories.categoryToken";
	}

	@Override
	public String getFacetCategory() {
		return "category";
	}

	@Override
	public void save(CategoryDTO t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(CategoryDTO t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(CategoryDTO t) {
		// TODO Auto-generated method stub
		
	}


}
