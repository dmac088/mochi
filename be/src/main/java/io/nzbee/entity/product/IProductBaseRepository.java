package io.nzbee.entity.product;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface IProductBaseRepository<P extends ProductEntity>  extends CrudRepository<P, Long> {

	List<P> findAll();
	
}

