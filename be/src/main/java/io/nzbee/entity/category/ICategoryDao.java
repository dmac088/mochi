package io.nzbee.entity.category;

import java.util.List;
import java.util.Optional;

import io.nzbee.entity.IDao;

public interface ICategoryDao extends IDao<Category> {

	List<Category> findByBrandIds(String hieararchyCode, String categoryTypeCode, List<Long> brandIds, Long level, String locale);
	
	Optional<Category> findByCategoryDesc(String categoryTypeCode, String categoryDesc, String locale);
	
	Optional<Category> findByCategoryCode(String categoryTypeCode, String categoryCode, String locale);
	
	List<Category> findByParent(String hieararchyCode, String categoryTypeCode, Long parentCategoryId, String locale);
	
	List<Category> findByLevel(String hieararchyCode, String categoryTypeCode, Long level, String locale);

	List<Category> findByCriteria(String hieararchyCode, String categoryTypeCode, String parentCategoryDesc,
			List<Long> brandIds, List<Long> tagIds, String locale);
	
}
