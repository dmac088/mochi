package io.nzbee.entity.brand;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface BrandRepository extends CrudRepository<Brand, Long> {

	List<Brand> findAll();

	Optional<Brand> findById(Long Id); 
	
	Optional<Brand> findByBrandCode(String brandCode);
	 
	List<Brand> findDistinctByProductsCategoriesCategoryIdIn(List<Long> categoryIds);
	
	List<Brand> findByProductsCategoriesAttributesLclCdAndProductsCategoriesAttributesCategoryDesc(String lcl, String categoryDesc);
	
}
