package io.nzbee.entity.category;

import java.util.List;
import java.util.Set;
import io.nzbee.entity.ILocalizedService;
import io.nzbee.entity.category.Category;

public interface ICategoryService extends ILocalizedService<Category> {

	Set<Category> recurseCategories(String currency, Set<Category> arrayList, Category pc);

	List<Category> findByParent(String locale, String parentCategoryCode);

	List<Category> findAllForLevel(String locale, String currency, Long level);
	
	List<Category> findAll(String locale, String currency, List<String> categoryCodes);

	List<Category> findAll(String locale, String parentCategoryDesc, List<String> brandCodes, List<String> tagCodes);
	
}
