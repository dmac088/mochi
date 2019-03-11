package io.javabrains.springbootstarter.domain;


import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {

	List<Product> findAll();
	
	List<Product> findAll(Specification<Product> spec);
	
	List<Product> findByPreviewFlag(Long previewFlag);
	
	Optional<Product> findByProductId(Long id);
}
