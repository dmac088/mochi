package io.nzbee.entity.category.brand;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface ICategoryBrandRepository extends CrudRepository<CategoryBrandEntity, Long> {

	List<CategoryBrandEntity> findAll();
	
	Optional<CategoryBrandEntity> findByCategoryCode(String code);
	
}
