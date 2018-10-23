package io.javabrains.springbootstarter.domain;


import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {

	ArrayList<Product> findByProductId(Long productId);
	
	ArrayList<Product> findByLclCd(String lng);
	
	ArrayList<Product> findAll(Specification<Product> spec);

	Optional<Product> findById(Long id);
}
