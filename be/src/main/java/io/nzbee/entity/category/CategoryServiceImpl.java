	package io.nzbee.entity.category;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import io.nzbee.entity.category.Category;
import io.nzbee.search.IFacetService;

@Service(value = "categoryEntityService")
public class CategoryServiceImpl implements ICategoryService, IFacetService {

	@Autowired
	@Qualifier(value = "categoryEntityPostgresDao")
	private ICategoryDao categoryDAO;
	
	@Override
	public Optional<Category> findById(String locale, long id) {
		return categoryDAO.findById(locale, id);
	}

	@Override
	public Optional<Category> findByCode(String locale, String code) {
		return categoryDAO.findByCode(locale, code);
	}
	
	@Override
	public Optional<Category> findByDesc(String locale, String categoryDesc) {
		return categoryDAO.findByDesc(locale, categoryDesc);
	}
	
	@Override
	public List<Category> findAll(String locale, String currency, String categoryCode, Set<String> categoryCodes, Set<String> brands,
			Set<String> tags, Double maxPrice) {
		return categoryDAO.findAll(locale, currency, categoryCode, categoryCodes, brands, tags, maxPrice);
	}
	
	@Override
	public Double getMaxPrice(String locale, String currency, String categoryCode, Set<String> categoryCodes, Set<String> brands,
			Set<String> tags) {
		return categoryDAO.getMaxPrice(locale, currency, categoryCode, categoryCodes, brands, tags);
	}
	
	@Override
	public Set<Category> findAll(String locale) {
		return categoryDAO.findAll(locale);
	}
	
	@Override
	public Set<Category> findAll(String locale, Set<String> categoryCodes) {
		return categoryDAO.findAll(locale, categoryCodes);
	}
	
	@Override
	public <T> List<Category> findAll(String locale, Class<T> classType) {
		return categoryDAO.findAllByType(locale, classType);
	}

	@Override
	public List<Category> findByParent(String locale, String parentCategoryCode) {
		return categoryDAO.findByParent(locale, parentCategoryCode);
	}

	@Override
	public List<Category> findAllForLevel(String locale, Long level) {
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
	public List<Category> findAll() {
		return categoryDAO.findAll();
	}

	@Override
	public Set<Category> findAll(String lcl, String currency, Set<String> codes) {
		return categoryDAO.findAll(lcl, codes);
	}

	@Override
	public Optional<Category> findByCode(String categoryCode) {
		return categoryDAO.findByCode(categoryCode);
	}

}
