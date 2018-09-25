package io.javabrains.springbootstarter.product;


import java.util.ArrayList;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {

	ArrayList<Product> findByProductId(Long productId);
	
	ArrayList<Product> findByLclCd(String lng);
	
	ArrayList<Product> findAll(Specification<Product> spec);
}
