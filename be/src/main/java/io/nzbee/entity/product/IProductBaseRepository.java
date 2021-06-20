package io.nzbee.entity.product;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface IProductBaseRepository<P extends ProductEntity>  extends CrudRepository<P, Long> {

	@Query(
	"SELECT p,"
	+ "		pa"
	+ "FROM ProductEntity p "
	+ " JOIN FETCH ProductAttributeEntity pa "		
	)
	Optional<P> find(String productCode);
	
	List<P> findAll();
	
}

