package io.nzbee.entity.category;

import java.util.List;
import io.nzbee.entity.ILocalizedDao;

public interface ICategoryDao extends ILocalizedDao<Category> {
	
	List<Category> findByParent(String locale, String parentCategoryCode);
	
	List<Category> findByLevel(String locale, Long level);

	List<Category> findChildrenByCriteria(String locale, String parentCategoryDesc,
										  List<String> brandCodes, List<String> tagCodes);

	List<Category> findAll(String locale, String currency);
	
	List<Category> findAll(String locale, String currency, List<String> categoryCodes);

	List<Category> findByCodeAndType(String locale, String currency, Class<Category> cls);
	
}
