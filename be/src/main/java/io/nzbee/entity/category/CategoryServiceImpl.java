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
	public Optional<Category> findById(String locale, String currency, long id) {
		return categoryDAO.findById(locale, currency, id);
	}

	@Override
	public Optional<Category> findByCode(String locale, String currency, String code) {
		return categoryDAO.findByCode(locale, currency, code);
	}
	
	@Override
	public Optional<Category> findByDesc(String locale, String currency, String categoryDesc) {
		return categoryDAO.findByDesc(locale, currency, categoryDesc);
	}
	
	@Override
	public List<Category> findAll(String locale, String currency) {
		return categoryDAO.findAll(locale, currency);
	}
	
	@Override
	public List<Category> findAll(String locale, String currency, Set<String> categoryCodes) {
		return categoryDAO.findAll(locale, currency, categoryCodes);
	}
	
	@Override
	public <T> List<Category> findAll(String locale, String currency, Class<T> classType) {
		return categoryDAO.findByCodeAndType(locale, currency, classType);
	}

	@Override
	public List<Category> findByParent(String locale, String parentCategoryCode) {
		return categoryDAO.findByParent(locale, parentCategoryCode);
	}

	@Override
	public List<Category> findAllForLevel(String locale, String currency, Long level) {
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
}
