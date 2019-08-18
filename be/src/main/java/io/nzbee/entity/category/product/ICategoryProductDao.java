package io.nzbee.entity.category.product;

import java.util.List;
import java.util.Optional;
import io.nzbee.entity.IDao;

public interface ICategoryProductDao extends IDao<CategoryProduct> {

	List<CategoryProduct> findByBrandIds(String hieararchyCode, String categoryTypeCode, List<Long> brandIds, Long level, String locale);
	
	Optional<CategoryProduct> findByCategoryDesc(String categoryTypeCode, String categoryDesc, String locale);
	
	Optional<CategoryProduct> findByCategoryCode(String categoryTypeCode, String categoryCode, String locale);
	
	List<CategoryProduct> findByParent(String hieararchyCode, String categoryTypeCode, Long parentCategoryId, String locale);
	
	List<CategoryProduct> findByLevel(String hieararchyCode, String categoryTypeCode, Long level, String locale);

	List<CategoryProduct> findChildrenByCriteria(String hieararchyCode, String categoryTypeCode, String parentCategoryDesc,
			List<String> brandCodes, List<String> tagCodes, String locale);
	
}
