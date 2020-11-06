package io.nzbee.entity.brand;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;

public interface IBrandRepository  extends CrudRepository<BrandEntity, Long>  {

	Set<BrandEntity> findAll();
	
	Optional<BrandEntity> findByAttributesLclCdAndAttributesBrandDesc(String locale, String brandDesc);
}
