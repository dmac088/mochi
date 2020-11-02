package io.nzbee.entity.category.brand;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

public interface ICategoryBrandRepository extends CrudRepository<CategoryBrandEntity, Long> {

	Set<CategoryBrandEntity> findAll();
	
	
}
