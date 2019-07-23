package io.nzbee.entity.category;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface ICategoryRepository extends CrudRepository<Category, Long> {

	List<Category> findAll();
	
	Category findByCategoryCode(String code);
	
	Category findByCategoryId(Long id);
}
