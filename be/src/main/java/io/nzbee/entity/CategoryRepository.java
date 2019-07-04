package io.nzbee.entity;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {

	List<Category> findAll();
	
	Category findByCategoryCode(String code);
	
}
