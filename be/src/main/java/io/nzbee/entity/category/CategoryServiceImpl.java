package io.nzbee.entity.category;

import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import io.nzbee.entity.category.Category;
import io.nzbee.search.IFacetService;

@Service(value = "categoryEntityService")
public class CategoryServiceImpl implements ICategoryService, IFacetService {
	
	public static final String CACHE_NAME = "categoryCache";
	
	@Autowired
	@Qualifier(value = "categoryEntityPostgresDao")
	private ICategoryDao categoryDAO;
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME, key = "{#locale, #categoryId}")
	public Optional<CategoryDTO> findById(String locale, Long categoryId) {
		return categoryDAO.findById(locale, categoryId);
	}

	@Override
	@Cacheable(cacheNames = CACHE_NAME, key = "{#locale, #categoryCode}")
	public Optional<CategoryDTO> findByCode(String locale, String categoryCode) {
		return categoryDAO.findByCode(locale, categoryCode);
	}
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME, key="#categoryCode")
	public Optional<Category> findByCode(String categoryCode) {
		return categoryDAO.findByCode(categoryCode);
	}
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME, key = "{#locale, #categoryDesc}")
	public Optional<CategoryDTO> findByDesc(String locale, String categoryDesc) {
		return categoryDAO.findByDesc(locale, categoryDesc);
	}
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME + "Other")
	public Set<CategoryDTO> findAll(String locale, String currency, String categoryCode, Set<String> categoryCodes, Set<String> brands,
			Set<String> tags, Double maxPrice) {
		return categoryDAO.findAll(locale, currency, categoryCode, categoryCodes, brands, tags, maxPrice);
	}
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME + "Other")
	public Double getMaxPrice(String locale, String currency, String categoryCode, Set<String> categoryCodes, Set<String> brands,
			Set<String> tags) {
		return categoryDAO.getMaxPrice(locale, currency, categoryCode, categoryCodes, brands, tags);
	}
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME + "Other")
	public Set<CategoryDTO> findAll(String locale) {
		return categoryDAO.findAll(locale);
	}
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME + "Other")
	public Set<CategoryDTO> findAll(String locale, Set<String> categoryCodes) {
		return categoryDAO.findAll(locale, categoryCodes);
	}
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME + "Other")
	public <T> Set<CategoryDTO> findAll(String locale, Class<T> classType) {
		return categoryDAO.findAllByType(locale, classType);
	}

	@Override
	@Cacheable(cacheNames = CACHE_NAME + "Other")
	public Set<Category> findByParent(String locale, String parentCategoryCode) {
		return categoryDAO.findByParent(locale, parentCategoryCode);
	}

	@Override
	@Cacheable(cacheNames = CACHE_NAME + "Other")
	public Set<Category> findAllForLevel(String locale, Long level) {
		return categoryDAO.findByLevel(locale, level);
	}
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME + "Other")
	public Set<Category> findAll() {
		return categoryDAO.findAll();
	}
	
	@Override
	public Set<CategoryDTO> findAll(String locale, String currency, Set<String> categoryCodes) {
		return categoryDAO.findAll(locale, categoryCodes);
	}

	
	@Override
	@Caching(evict = {
			@CacheEvict(cacheNames = CACHE_NAME + "Other", 	allEntries = true),
			@CacheEvict(cacheNames = CACHE_NAME, key="#category.categoryCode"),
			@CacheEvict(cacheNames = CACHE_NAME, key="{#category.locale, #category.categoryId}"),
			@CacheEvict(cacheNames = CACHE_NAME, key="{#category.locale, #category.categoryCode}")
	})
	public void save(Category category) {
		categoryDAO.save(category);
	}

	@Override
	public void update(Category t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Category t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getFacetField() {
		return "product.categories.categoryToken";
	}

	@Override
	public String getFacetCategory() {
		return "category";
	}

	@Override
	public String tokenToCode(String token) {
		return token.substring(token.lastIndexOf('/')+1,token.length());
	}



}
