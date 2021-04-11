package io.nzbee.entity.brand.view;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import io.nzbee.entity.brand.BrandEntity;
import io.nzbee.entity.brand.view.BrandViewDTO;

public interface IBrandViewRepository  extends CrudRepository<BrandEntity, Long>  {
	
	@Query(	  " SELECT new io.nzbee.entity.brand.view.BrandViewDTO("
			+ "												be.brandId, "
			+ "												be.brandCode, "
			+ "												at.brandDesc, "
			+ "												at.lclCd "		
			+ ") "
			+ " FROM BrandEntity be "
			+ " JOIN be.attributes at "
			+ " WHERE at.lclCd = :locale")
	List<BrandViewDTO> findAll(String locale);
	
	@Query(	  " SELECT DISTINCT new io.nzbee.entity.view.domain.BrandViewDTO("
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
	List<BrandViewDTO> findAllByProductType(String locale, String cls);
	
}
