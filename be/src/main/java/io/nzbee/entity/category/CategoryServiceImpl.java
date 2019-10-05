package io.nzbee.entity.category;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.nzbee.entity.category.Category;

@Service(value = "categoryEntityService")
public class CategoryServiceImpl implements ICategoryService {

	@Autowired
	private CategoryDaoImpl categoryDAO;
	
	@Override
	public Optional<Category> findById(long id) {
		// TODO Auto-generated method stub
		return categoryDAO.findById(id);
	}

	@Override
	public List<Category> getAll() {
		// TODO Auto-generated method stub
		return categoryDAO.getAll();
	}

	@Override
	public List<Category> findAll(String locale, String currency) {
		// TODO Auto-generated method stub
		return categoryDAO.findAll(locale, currency);
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
	public Optional<Category> findByCategoryDesc(String locale, String categoryDesc) {
		// TODO Auto-generated method stub
		return categoryDAO.findByCategoryDesc(locale, categoryDesc);
	}

	@Override
	public Optional<Category> findByCategoryCode(String locale, String categoryCode) {
		// TODO Auto-generated method stub
		return categoryDAO.findByCategoryCode(locale, categoryCode);
	}


	@Override
	public List<Category> findAllForLevel(String locale, Long level) {
		// TODO Auto-generated method stub
		return categoryDAO.findByLevel(locale, level);
	}
	
	@Override
	public Set<Category> recurseCategories(String currency, Set<Category> arrayList, Category pc) {
		if(pc == null) { return arrayList; }
		
		List<Category> lc = categoryDAO.getChildren(currency, pc);
		
		arrayList.add(pc);
		if(lc.isEmpty()) { return arrayList; }
		lc.stream().forEach(c -> {
			arrayList.add(c);
			recurseCategories(currency, arrayList, c); 
		});
		return arrayList; 
	}
	

}
