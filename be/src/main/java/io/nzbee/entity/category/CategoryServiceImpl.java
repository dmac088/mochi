package io.nzbee.entity.category;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.nzbee.entity.StringCollectionWrapper;
import io.nzbee.entity.category.CategoryEntity;
import io.nzbee.search.IFacetService;

@Service(value = "categoryEntityService")
@Transactional
public class CategoryServiceImpl implements ICategoryService, IFacetService {
	
	public static final String CACHE_NAME = "categoryCache";
	
	@Autowired
	@Qualifier(value = "categoryEntityPostgresDao")
	private ICategoryDao categoryDAO;
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME, key = "#categoryId.toString()")
	public Optional<CategoryEntity> findById(Long categoryId) {
		return categoryDAO.findById(categoryId);
	}
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME, key = "#locale + \", \" + #categoryId.toString()")
	public Optional<CategoryDTO> findById(String locale, Long categoryId) {
		return categoryDAO.findById(locale, categoryId);
	}
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME, key = "#locale + \", \" + #categoryCode")
	public Optional<CategoryDTO> findByCode(String locale, String categoryCode) {
		return categoryDAO.findByCode(locale, categoryCode);
	}

	@Override
	@Cacheable(cacheNames = CACHE_NAME, key = "#categoryCode")
	public Optional<CategoryEntity> findByCode(String categoryCode) {
		return categoryDAO.findByCode(categoryCode);
	}
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME, key = "#locale + \", \" + #categoryDesc")
	public Optional<CategoryDTO> findByDesc(String locale, String categoryDesc) {
		return categoryDAO.findByDesc(locale, categoryDesc);
	}
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME + "Other", key="#locale + \", \" + #currency + \", \" + #categoryCode + \", \" + #categoryCodes.getCacheKey() + \", \" + #brandCodes.getCacheKey() + \", \" + #tagCodes.getCacheKey() + \", \" + #maxPrice")
	public List<CategoryDTO> findAll(String locale, String currency, String categoryCode, StringCollectionWrapper categoryCodes, StringCollectionWrapper brandCodes, StringCollectionWrapper tagCodes, Double maxPrice) {
		return categoryDAO.findAll(locale, currency, categoryCode, categoryCodes, brandCodes, tagCodes, maxPrice);
	}
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME + "Other", key="#locale + \", \" + #currency + \", \" + #categoryCode + \", \" + #categoryCodes.getCacheKey() + \", \" + #brandCodes.getCacheKey() + \", \" + #tagCodes.getCacheKey()")
	public Double getMaxPrice(String locale, String currency, String categoryCode, StringCollectionWrapper categoryCodes, StringCollectionWrapper brandCodes, StringCollectionWrapper tagCodes) {
		return categoryDAO.getMaxPrice(locale, currency, categoryCode, categoryCodes, brandCodes, tagCodes);
	}
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME + "Other")
	public List<CategoryDTO> findAll(String locale) {
		return categoryDAO.findAll(locale);
	}
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME + "Other", key="#locale + \", \" + #categoryCodes.getCacheKey()")
	public List<CategoryDTO> findAll(String locale, StringCollectionWrapper categoryCodes) {
		return categoryDAO.findAll(locale, categoryCodes);
	}
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME + "Other", key="#locale + \", \" + rootCategory + \", \" + #classType.getName()")
	public <T> List<CategoryDTO> findAll(String locale, String rootCategory, Class<T> classType) {
		return categoryDAO.findAllByType(locale, rootCategory, classType);
	}

	@Override
	@Cacheable(cacheNames = CACHE_NAME + "Other", key = "\"findByParent\" + #locale + \", \" + #parentCategoryCode")
	public List<CategoryEntity> findByParent(String locale, String parentCategoryCode) {
		return categoryDAO.findByParent(locale, parentCategoryCode);
	}

	@Override
	@Cacheable(cacheNames = CACHE_NAME + "Other", key = "#locale + \", \" + #level.toString()")
	public List<CategoryEntity> findAllForLevel(String locale, Long level) {
		return categoryDAO.findByLevel(locale, level);
	}
	
	@Override
	public List<CategoryEntity> findAll() {
		return categoryDAO.findAll();
	}
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME + "Other", key="#locale + \", \" + #rootCategory + \", \" + #codes.getCacheKey()")
	public List<CategoryDTO> findAll(String locale, String currency, String rootCategory, StringCollectionWrapper codes) {
		return categoryDAO.findAll(locale, codes);
	}

	
	@Override
	@Caching(evict = {
			@CacheEvict(cacheNames = CACHE_NAME + "Other", 	allEntries = true),
			@CacheEvict(cacheNames = CACHE_NAME, key="#category.categoryId.toString()"),
			@CacheEvict(cacheNames = CACHE_NAME, key="#category.categoryCode"),
			@CacheEvict(cacheNames = CACHE_NAME, key="#category.locale + \", \" + #category.categoryId.toString()"),
			@CacheEvict(cacheNames = CACHE_NAME, key="#category.locale + \", \" + #category.categoryCode")
	})
	public void save(CategoryEntity category) {
		categoryDAO.save(category);
	}

	@Override
	public void update(CategoryEntity t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(CategoryEntity t) {
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

	@Override
	public List<CategoryEntity> findAll(Set<String> codes) {
		return categoryDAO.findAll(codes);
	}


}
