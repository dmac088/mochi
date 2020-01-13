package io.nzbee.entity.product.status;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;


public interface IProductStatusRepository extends CrudRepository<ProductStatus, Long> {
	
	Optional<ProductStatus> findByCode(String code); 
	
}
