package io.nzbee.dto.category;

import java.util.List;
import java.util.Optional;
import io.nzbee.entity.IDao;

public interface ICategoryWithNameAndStatsDao extends IDao<CategoryWithNameAndStats> {

	Optional<CategoryWithNameAndStats> findByCategoryDesc(String categoryDesc, String locale);
	
	Optional<CategoryWithNameAndStats> findByCategoryCode(String categoryCode, String locale);
	
	List<CategoryWithNameAndStats> findByParent(String categoryTypeCodeProduct, String parentCategoryCode, String locale);
	
	List<CategoryWithNameAndStats> findByLevel(String categoryTypeCode, Long level, String locale);

	Optional<CategoryWithNameAndStats> findById(long id, String locale);

	List<CategoryWithNameAndStats> findChildrenByCriteria(String parentCategoryDesc, List<String> brandCodes, List<String> tagCodes,
			String locale);

	List<CategoryWithNameAndStats> findAll(String locale, String currency);

	List<CategoryWithName> findAll(String locale);

}
