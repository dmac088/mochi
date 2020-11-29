package io.nzbee.entity.category.attribute;

import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface CategoryAttributeRepository extends CrudRepository<CategoryAttributeEntity, Long> {

	List<CategoryAttributeEntity> findAll();

	List<CategoryAttributeEntity> findByLclCd(String lcl);

	Optional<CategoryAttributeEntity> findByLclCdAndCategoryCategoryId(String lcl, Long id);
	
	Optional<CategoryAttributeEntity> findByLclCdAndCategoryCategoryCode(String lcl, String code);

}
