package io.nzbee.entity.category;

import java.util.List;
import java.util.Optional;
import io.nzbee.entity.IDao;

public interface ICategoryDao extends IDao<Category> {

	Optional<Category> findByCategoryDesc(String locale, String categoryDesc);
	
	Optional<Category> findByCategoryCode(String locale, String categoryCode);
	
	List<Category> findByParent(String locale, String parentCategoryCode);
	
	List<Category> findByLevel(String locale, Long level);

	List<Category> findChildrenByCriteria(String locale, String parentCategoryDesc,
			List<String> brandCodes, List<String> tagCodes);

	List<Category> findAll(String locale, String currency);

	List<Category> getChildren(String currency, Category category);

	
}
