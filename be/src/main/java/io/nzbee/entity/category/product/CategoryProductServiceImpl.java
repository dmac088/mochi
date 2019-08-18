package io.nzbee.entity.category.product;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.nzbee.entity.category.Category;

@Service(value = "categoryEntityService")
public class CategoryProductServiceImpl implements ICategoryProductService {

	@Autowired
	private CategoryProductDaoImpl categoryDAO;
	
	@Override
	public Optional<CategoryProduct> findById(long id) {
		// TODO Auto-generated method stub
		return categoryDAO.findById(id);
	}

	@Override
	public List<CategoryProduct> getAll() {
		// TODO Auto-generated method stub
		return categoryDAO.getAll();
	}

	@Override
	public List<CategoryProduct> findAll() {
		// TODO Auto-generated method stub
		return categoryDAO.findAll();
	}

	@Override
	public List<CategoryProduct> findByParent(String hieararchyCode, String categoryTypeCode, Long parentCategoryId,
			String locale) {
		// TODO Auto-generated method stub
		return categoryDAO.findByParent(hieararchyCode, categoryTypeCode, parentCategoryId, locale);
	}

	@Override
	public Optional<CategoryProduct> findByCategoryDesc(String categoryTypeCode, String categoryDesc, String locale) {
		// TODO Auto-generated method stub
		return categoryDAO.findByCategoryDesc(categoryTypeCode, categoryDesc, locale);
	}

	@Override
	public Optional<CategoryProduct> findByCategoryCode(String categoryTypeCode, String categoryCode, String locale) {
		// TODO Auto-generated method stub
		return categoryDAO.findByCategoryCode(categoryTypeCode, categoryCode, locale);
	}

	@Override
	public List<CategoryProduct> find(String hieararchyCode, String categoryTypeCode, String parentCategoryDesc,
			List<String> brandCodes, List<String> tagCodes, String locale) {
		return categoryDAO.findChildrenByCriteria(hieararchyCode, categoryTypeCode, parentCategoryDesc, brandCodes, tagCodes, locale);
	}

	@Override
	public List<CategoryProduct> findByLevel(String hieararchyCode, String categoryTypeCode, Long level, String locale) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Set<Category> recurseCategories(Set<Category> arrayList, Category pc) {
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
