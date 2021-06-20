package io.nzbee.entity.product;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface IProductBaseRepository<P extends ProductEntity>  extends CrudRepository<P, Long> {

	@Query(
	" SELECT p " 
	+ " FROM ProductEntity p "
	+ " INNER JOIN FETCH p.attributes pa "
	+ " WHERE pa.lclCd = :locale "
	)
	Optional<P> find(String productCode);
	
	List<P> findAll();
	
}

