package io.javabrains.springbootstarter.entity;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface BrandRepository extends CrudRepository<Brand, Long> {

	List<Brand> findAll();

	Brand findByBrandId(Long brandId);
	
	Brand findByBrandCode(String brandCode);
	
	List<Brand> findByProductsCategoriesAttributesLclCdAndProductsCategoriesAttributesCategoryDesc(String lcl, String categoryDesc);
	
}
