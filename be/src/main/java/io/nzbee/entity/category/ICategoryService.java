package io.nzbee.entity.category;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import io.nzbee.entity.category.Category;

public interface ICategoryService {

	Optional<Category> findById(long id);
	
	List<Category> findAll(String locale, String currency);
	
	List<Category> getAll();
	
	Optional<Category> findByCategoryDesc(String categoryDesc, String locale);
	
	Optional<Category> findByCategoryCode(String categoryCode, String locale);

	Set<Category> recurseCategories(Set<Category> arrayList, Category pc, String currency);

	List<Category> findByParent(String parentCategoryCode, String locale);

	List<Category> findAllForLevel(Long level, String locale);

	List<Category> findAll(String parentCategoryDesc, List<String> brandCodes, List<String> tagCodes, String locale);
	
}
