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
	+ " LEFT JOIN FETCH p.attributes pa "
	+ " LEFT JOIN FETCH p.brand b "
	+ " LEFT JOIN FETCH p.department d  "
	+ " LEFT JOIN FETCH p.productStatus s "
	+ " LEFT JOIN FETCH p.categories c "
	+ " LEFT JOIN FETCH p.prices prc "
	+ " LEFT JOIN FETCH prc.currency curr "
	+ " LEFT JOIN FETCH prc.type prct "
	+ " WHERE p.productUPC = :productCode "
	)
	Optional<P> findByCode(String productCode);
	
	List<P> findAll();
	
}

