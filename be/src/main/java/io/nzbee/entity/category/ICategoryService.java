package io.nzbee.entity.category;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import io.nzbee.entity.category.Category;

public interface ICategoryService {

	Optional<Category> findById(long id);
	
	List<Category> findAll(String locale, String currency);
	
	List<Category> getAll();
	
	List<Category> findByParent(String hieararchyCode, String categoryTypeCode, Long parentCategoryId, String locale);
	
	Optional<Category> findByCategoryDesc(String categoryTypeCode, String categoryDesc, String locale);
	
	Optional<Category> findByCategoryCode(String categoryTypeCode, String categoryCode, String locale);
	
	List<Category> findByLevel(String hieararchyCode, String categoryTypeCode, Long level, String locale);

	List<Category> find(String hieararchyCode, String categoryTypeCode, String parentCategoryDesc,
			List<String> brandCodes, List<String> tagCodes, String locale);

	Set<Category> recurseCategories(Set<Category> arrayList, Category pc);
	
}
