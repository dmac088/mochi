package io.nzbee.entity.category.attribute;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface CategoryAttributeRepository extends CrudRepository<CategoryAttribute, Long> {

	List<CategoryAttribute> findAll();

	List<CategoryAttribute> findByLclCd(String lcl);

	Optional<CategoryAttribute> findByLclCdAndCategoryCategoryId(String lcl, Long id);
	
	Optional<CategoryAttribute> findByLclCdAndCategoryCategoryCode(String lcl, String code);

}
