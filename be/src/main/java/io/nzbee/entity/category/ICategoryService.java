package io.nzbee.entity.category;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import io.nzbee.entity.category.Category;

public interface ICategoryService {

	Optional<Category> findById(long id);
	
	List<Category> findAll(String locale, String currency);
	
	List<Category> getAll();
	
	Optional<Category> findByCategoryDesc(String locale, String categoryDesc);
	
	Optional<Category> findByCategoryCode(String locale, String categoryCode);

	Set<Category> recurseCategories(String currency, Set<Category> arrayList, Category pc);

	List<Category> findByParent(String locale, String parentCategoryCode);

	List<Category> findAllForLevel(String locale, Long level);

	List<Category> findAll(String locale, String parentCategoryDesc, List<String> brandCodes, List<String> tagCodes);
	
}
