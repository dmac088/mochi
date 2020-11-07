package io.nzbee.entity.category;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface ICategoryRepository extends CrudRepository<CategoryEntity, Long>  {

	Optional<CategoryEntity> findByCategoryCode(String code);
	
}
