package io.nzbee.entity.product.physical.light;

import java.util.List;
import java.util.Set;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import io.nzbee.entity.product.physical.PhysicalProductEntity;

public interface IPhysicalProductLightRepository extends CrudRepository<PhysicalProductEntity, Long> {
	
	@Query(name = "QueryForListOfProducts", nativeQuery = true)
	   List<PhysicalProductLightDTO> findAll(
	      @Param("locale")	 	 	 String locale, 
	      @Param("currency") 	 	 String currency, 
	      @Param("categoryCode") 	 String categoryCode,
	      @Param("productCodes") 	 Set<String> productCodes);
	
}

 