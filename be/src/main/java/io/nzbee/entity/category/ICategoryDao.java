package io.nzbee.entity.category;

import java.util.List;
import java.util.Optional;
import io.nzbee.entity.IDao;

public interface ICategoryDao extends IDao<Category> {

	Optional<Category> findByCategoryDesc(String categoryTypeCode, String categoryDesc, String locale);
	
	Optional<Category> findByCategoryCode(String categoryTypeCode, String categoryCode, String locale);
	
	List<Category> findByParent(String hieararchyCode, String categoryTypeCode, Long parentCategoryId, String locale);
	
	List<Category> findByLevel(String hieararchyCode, String categoryTypeCode, Long level, String locale);

	List<Category> findChildrenByCriteria(String hieararchyCode, String categoryTypeCode, String parentCategoryDesc,
			List<String> brandCodes, List<String> tagCodes, String locale);

	
}
