package io.nzbee.entity.brand;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

public interface IBrandRepository  extends CrudRepository<BrandEntity, Long>  {

	Set<BrandEntity> findAll();
	
}
