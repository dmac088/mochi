package io.nzbee.entity.brand;

import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface IBrandRepository  extends CrudRepository<BrandEntity, Long>  {

	List<BrandEntity> findAll();
	
	Optional<BrandEntity> findByBrandCode(String brandCode);
	
	Optional<BrandEntity> findByAttributesLclCdAndAttributesBrandDesc(String locale, String brandDesc);

}
