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
	public List<Category> findByParent(String hieararchyCode, String categoryTypeCode, Long parentCategoryId,
			String locale) {
		// TODO Auto-generated method stub
		return categoryDAO.findByParent(hieararchyCode, categoryTypeCode, parentCategoryId, locale);
	}

	@Override
	public Optional<Category> findByCategoryDesc(String categoryTypeCode, String categoryDesc, String locale) {
		// TODO Auto-generated method stub
		return categoryDAO.findByCategoryDesc(categoryTypeCode, categoryDesc, locale);
	}

	@Override
	public Optional<Category> findByCategoryCode(String categoryTypeCode, String categoryCode, String locale) {
		// TODO Auto-generated method stub
		return categoryDAO.findByCategoryCode(categoryTypeCode, categoryCode, locale);
	}


	@Override
	public List<Category> findByLevel(String hieararchyCode, String categoryTypeCode, Long level, String locale) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Set<Category> recurseCategories(Set<Category> arrayList, Category pc, String currency) {
		if(pc == null) { return arrayList; }
		
		List<Category> lc = categoryDAO.getChildren(pc, currency);
		
		arrayList.add(pc);
		if(lc.isEmpty()) { return arrayList; }
		lc.stream().forEach(c -> {
			arrayList.add(c);
			recurseCategories(arrayList, c, currency); 
		});
		return arrayList; 
	}

	@Override
	public List<Category> find(String hieararchyCode, String categoryTypeCode, String parentCategoryDesc,
			List<String> brandCodes, List<String> tagCodes, String locale) {
		// TODO Auto-generated method stub
		return categoryDAO.findChildrenByCriteria(hieararchyCode, categoryTypeCode, parentCategoryDesc, brandCodes, tagCodes, locale);
	}
	

}
