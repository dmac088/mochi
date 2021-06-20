package io.nzbee.entity.product.status;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;


public interface IProductStatusRepository extends CrudRepository<ProductStatusEntity, Long> {
	
	Optional<ProductStatusEntity> findByProductStatusCode(String code); 
	
	List<ProductStatusEntity> findAll();
	
}
