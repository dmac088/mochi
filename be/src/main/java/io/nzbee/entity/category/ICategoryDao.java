package io.nzbee.entity.category;

import java.util.List;
import java.util.Optional;
import io.nzbee.entity.IDao;

public interface ICategoryDao extends IDao<Category> {

	Optional<Category> findByCategoryDesc(String categoryDesc, String locale);
	
	Optional<Category> findByCategoryCode(String categoryCode, String locale);
	
	List<Category> findByParent(String parentCategoryCode, String locale);
	
	List<Category> findByLevel(Long level, String locale);

	List<Category> findChildrenByCriteria(String parentCategoryDesc,
			List<String> brandCodes, List<String> tagCodes, String locale);

	List<Category> findAll(String locale, String currency);

	List<Category> getChildren(Category category, String currency);

	
}
