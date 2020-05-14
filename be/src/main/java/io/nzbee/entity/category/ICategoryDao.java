package io.nzbee.entity.category;

import java.util.List;
import java.util.Set;

import io.nzbee.entity.ILocalizedDao;

public interface ICategoryDao extends ILocalizedDao<Category> {
	
	List<Category> findByParent(String locale, String parentCategoryCode);
	
	List<Category> findByLevel(String locale, Long level);

	List<Category> findAll(String locale, String currency);
	
	List<Category> findAll(String locale, String currency, Set<String> categoryCodes);
	
	List<Category> findAllByProductCode(String locale, String currency, String productCode);

	<T> List<Category> findByCodeAndType(String locale, String currency, Class<T> cls);
	
}
