package io.javabrains.springbootstarter.domain;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface BrandAttributeRepository extends CrudRepository<BrandAttribute, Long> {

	List<BrandAttribute> findAll();

	List<BrandAttribute> findByLclCd(String lcl);

}
