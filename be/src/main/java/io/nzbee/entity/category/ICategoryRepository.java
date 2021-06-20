package io.nzbee.entity.category;

import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;



public interface ICategoryRepository<T extends CategoryEntity> extends CrudRepository<T, Long>  {
	
	List<T> findAll();

	Optional<T> findByCategoryId(Long Id);
	
	Optional<T> findByCategoryCode(String code);
	
	Optional<T> findByAttributesLclCdAndAttributesCategoryDesc(String locale, String desc);
	
}
