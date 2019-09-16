package io.nzbee.dto.category;

import java.util.List;
import java.util.Optional;
import io.nzbee.entity.IDao;

public interface ICategoryDao extends IDao<Category> {

	Optional<Category> findByCategoryDesc(String categoryDesc, String locale);
	
	Optional<Category> findByCategoryCode(String categoryCode, String locale);
	
	List<Category> findByParent(String categoryTypeCodeProduct, String parentCategoryCode, String locale);
	
	List<Category> findByLevel(String categoryTypeCode, Long level, String locale);

	Optional<Category> findById(long id, String locale);

	List<Category> findChildrenByCriteria(String parentCategoryDesc, List<String> brandCodes, List<String> tagCodes,
			String locale);

	List<Category> findAll(String locale);

	

	
}
