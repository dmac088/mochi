package io.nzbee.entity.category;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public List<Category> findAll() {
		// TODO Auto-generated method stub
		return categoryDAO.findAll();
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
	public List<Category> find(String hieararchyCode, String categoryTypeCode, String parentCategoryDesc,
			List<Long> brandIds, List<Long> tagIds, String locale) {
		// TODO Auto-generated method stub
		return categoryDAO.find(hieararchyCode, categoryTypeCode, parentCategoryDesc, brandIds, tagIds, locale);
	}

	@Override
	public List<Category> findByLevel(String hieararchyCode, String categoryTypeCode, Long level, String locale) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static List<Category> recurseCategories(List<Category> arrayList, Category pc) {
		if(pc == null) { return arrayList; }
		arrayList.add(pc);
		if(pc.getChildren().isEmpty()) { return arrayList; }
		pc.getChildren().stream().forEach(c -> {
			arrayList.add(c);
			recurseCategories(arrayList, c); 
		});
		return arrayList; 
	}
	

}
