package io.nzbee.entity.category.type;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface ICategoryTypeRepository extends CrudRepository<CategoryType, Long> {
	
	List<CategoryType> findAll(); 
	 
	Optional<CategoryType> findById(Long id);
}

