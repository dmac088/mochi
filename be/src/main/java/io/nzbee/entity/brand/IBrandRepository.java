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
	
	@Query(	  " SELECT DISTINCT new io.nzbee.entity.brand.BrandDTO("
			+ "												be.brandId, "
			+ "												be.brandCode, "
			+ "												at.brandDesc, "
			+ "												at.lclCd "		
			+ ") "
			+ " FROM BrandEntity be "
			+ " JOIN be.attributes at "
			+ " JOIN be.products p "
			+ " JOIN p.department d "
			+ " WHERE at.lclCd = :locale "
			+ " AND d.departmentClass = :cls ")
	List<BrandDTO> findAllByProductType(String locale, String cls);
	
	
	
	
	
	
}
