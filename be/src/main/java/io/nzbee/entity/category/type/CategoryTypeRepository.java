package io.nzbee.entity.category.type;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface CategoryTypeRepository extends CrudRepository<CategoryType, Long> {
	
	List<CategoryType> findAll();
	
	CategoryType findByCategoryTypeId(Long id);
}

