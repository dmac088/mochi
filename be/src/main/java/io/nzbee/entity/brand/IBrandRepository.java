package io.nzbee.entity.brand;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IBrandRepository  extends CrudRepository<BrandEntity, Long>  {

	List<BrandEntity> findAll();
	
	Optional<BrandEntity> findByAttributesLclCdAndAttributesBrandDesc(String locale, String brandDesc);

	
	@Query(	  " SELECT new io.nzbee.entity.brand.BrandDTO("
			+ "												be.brandId, "
			+ "												be.brandCode, "
			+ "												at.brandDesc, "
			+ "												at.lclCd "		
			+ ") "
			+ " FROM BrandEntity be "
			+ " JOIN be.attributes at "
			+ " WHERE at.lclCd = :locale")
	List<BrandDTO> findAll(String locale);
	
	
}
