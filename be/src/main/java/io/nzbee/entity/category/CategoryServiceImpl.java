package io.nzbee.entity.category;

import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
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
	@Cacheable(cacheNames = CACHE_NAME, key = "{#locale, #id}")
	public Optional<Category> findById(String locale, long id) {
		return categoryDAO.findById(locale, id);
	}

	@Override
	@Cacheable(cacheNames = CACHE_NAME, key = "{#locale, #code}")
	public Optional<Category> findByCode(String locale, String code) {
		return categoryDAO.findByCode(locale, code);
	}
	
	@Override
	public Optional<Category> findByDesc(String locale, String desc) {
		return categoryDAO.findByDesc(locale, desc);
	}
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME + "Other")
	public Set<Category> findAll(String locale, String currency, String categoryCode, Set<String> categoryCodes, Set<String> brands,
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
	public Set<Category> findAll(String locale) {
		return categoryDAO.findAll(locale);
	}
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME + "Other")
	public Set<Category> findAll(String locale, Set<String> categoryCodes) {
		return categoryDAO.findAll(locale, categoryCodes);
	}
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME + "Other")
	public <T> Set<Category> findAll(String locale, Class<T> classType) {
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
	public void save(Category t) {
		// TODO Auto-generated method stub
		
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

	@Override
	@Cacheable(cacheNames = CACHE_NAME + "Other")
	public Set<Category> findAll() {
		return categoryDAO.findAll();
	}

	@Override
	@Cacheable(cacheNames = CACHE_NAME + "Other")
	public Set<Category> findAll(String lcl, String currency, Set<String> codes) {
		return categoryDAO.findAll(lcl, codes);
	}

	@Override
	@Cacheable(cacheNames = CACHE_NAME, key="#categoryCode")
	public Optional<Category> findByCode(String categoryCode) {
		return categoryDAO.findByCode(categoryCode);
	}

}
