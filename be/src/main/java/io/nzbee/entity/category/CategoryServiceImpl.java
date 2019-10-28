package io.nzbee.entity.category;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import io.nzbee.entity.category.Category;

@Service(value = "categoryEntityService")
public class CategoryServiceImpl implements ICategoryService {

	@Autowired
	@Qualifier(value = "categoryEntityPostgresDao")
	private CategoryDaoPostgresImpl categoryDAO;
	
	@Override
	public Optional<Category> findById(String locale, String currency, long id) {
		// TODO Auto-generated method stub
		return categoryDAO.findById(locale, currency, id);
	}

	@Override
	public List<Category> findAll(String locale, String currency) {
		// TODO Auto-generated method stub
		return categoryDAO.findAll(locale, currency);
	}
	
	@Override
	public List<Category> findAll(String locale, String currency, List<String> categoryCodes) {
		// TODO Auto-generated method stub
		return categoryDAO.findAll(locale, currency, categoryCodes);
	}
	
	@Override
	public List<Category> findAll(String locale, String parentCategoryDesc, List<String> brandCodes, List<String> tagCodes) {
		// TODO Auto-generated method stub
		return categoryDAO.findChildrenByCriteria(locale, parentCategoryDesc, brandCodes, tagCodes);
	}

	@Override
	public List<Category> findByParent(String locale, String parentCategoryCode) {
		// TODO Auto-generated method stub
		return categoryDAO.findByParent(locale, parentCategoryCode);
	}

	@Override
	public Optional<Category> findByDesc(String locale, String currency, String categoryDesc) {
		// TODO Auto-generated method stub
		return categoryDAO.findByDesc(locale, currency, categoryDesc);
	}

	@Override
	public Optional<Category> findByCode(String locale, String currency, String code) {
		// TODO Auto-generated method stub
		return categoryDAO.findByCode(locale, currency, code);
	}

	@Override
	public List<Category> findAllForLevel(String locale, String currency, Long level) {
		// TODO Auto-generated method stub
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

}
