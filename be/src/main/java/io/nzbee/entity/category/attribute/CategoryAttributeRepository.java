package io.nzbee.entity.category.attribute;

import java.util.Optional;
import java.util.Set;
import org.springframework.data.repository.CrudRepository;

public interface CategoryAttributeRepository extends CrudRepository<CategoryAttributeEntity, Long> {

	Set<CategoryAttributeEntity> findAll();

	Set<CategoryAttributeEntity> findByLclCd(String lcl);

	Optional<CategoryAttributeEntity> findByLclCdAndCategoryCategoryId(String lcl, Long id);
	
	Optional<CategoryAttributeEntity> findByLclCdAndCategoryCategoryCode(String lcl, String code);

}
