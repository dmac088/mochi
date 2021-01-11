package io.nzbee.entity.category;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface ICategoryRepository extends CrudRepository<CategoryEntity, Long>  {

	Optional<CategoryEntity> findByCategoryId(Long Id);
	
	Optional<CategoryEntity> findByCategoryCode(String code);
	
	Optional<CategoryEntity> findByAttributesLclCdAndAttributesCategoryDesc(String locale, String desc);
	
}
