package io.nzbee.entity.category.product;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

import io.nzbee.entity.category.Category;

public interface ICategoryProductRepository extends CrudRepository<Category, Long> {

	List<Category> findAll();
	
	Category findByCategoryCode(String code);
	
	Category findByCategoryId(Long id);
}
