package io.nzbee.entity.product.tag;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface ProductTagRepository extends CrudRepository<ProductTag, Long> {

	List<ProductTag> findAll();
	
}
