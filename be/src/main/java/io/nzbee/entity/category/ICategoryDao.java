package io.nzbee.entity.category;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import io.nzbee.entity.IDao;

public interface ICategoryDao extends IDao<CategoryDTO> {
	
	List<CategoryEntity> findByParent(String locale, String parentCategoryCode);
	
	List<CategoryEntity> findByLevel(String locale, Long level);

	List<CategoryDTO> findAll(String locale);
	
	List<CategoryEntity> findAllByProductCode(String locale, String productCode);

	<T> List<CategoryDTO> findAllByType(String locale, String rootCategory, Class<T> cls);

	List<CategoryEntity> findAll();

	Optional<CategoryEntity> findByCode(String categoryCode);

	Optional<CategoryEntity> findById(Long id);

	List<CategoryEntity> findAll(Set<String> codes);

	Optional<CategoryDTO> findById(String locale, Long categoryId);

	Optional<CategoryDTO> findByCode(String locale, String categoryCode);

	Optional<CategoryDTO> findByDesc(String locale, String categoryDesc);

	
}
