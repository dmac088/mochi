package io.javabrains.springbootstarter.domain;


import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {

	ArrayList<Product> findByLclCd(String lng);
	
	ArrayList<Product> findByLclCdAndCategoriesCategoryDesc(String lng, String categoryDesc);
	
	ArrayList<Product> findByLclCdAndCategoriesCategoryId(String lng, Long categoryId);
	
	ArrayList<Product> findAll(Specification<Product> spec);
	
	ArrayList<Product> findByCategoriesCategoryDesc(String categoryDesc);

	Optional<Product> findByProductId(Long id);
}
