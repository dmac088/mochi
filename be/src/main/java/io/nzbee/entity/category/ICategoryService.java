package io.nzbee.entity.category;

import java.util.List;
import java.util.Optional;

import io.nzbee.entity.IService;

public interface ICategoryService extends IService<CategoryEntity> {

	Optional<CategoryEntity> findById(Long categoryId);
	
	Optional<CategoryEntity> findByCode(String categoryCode);

	List<CategoryEntity> findAll();

}
