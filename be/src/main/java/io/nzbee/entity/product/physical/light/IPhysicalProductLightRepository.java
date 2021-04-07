package io.nzbee.entity.product.physical.light;

import java.util.List;
import java.util.Set;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import io.nzbee.entity.product.physical.PhysicalProductEntity;

public interface IPhysicalProductLightRepository extends CrudRepository<PhysicalProductEntity, Long> {
		
	/*
	String productUPC, 
	String productDesc, 
	BrandDTO brand, 
	Double retailPrice,
	Double markdownPrice, 
	Boolean inStock, 
	String productImage
	*/
	
	@Query(	  " SELECT new  io.nzbee.entity.product.physical.light.PhysicalProductLightDTO("
			+ "															 , "
			+ "															 , "
			+ "															 , "
			+ "															 "		
			+ ") "
			+ " FROM ShippingProductEntity sp "
			+ " JOIN sp.attributes at "
			+ " WHERE at.lclCd = :locale")
	List<PhysicalProductLightDTO> findAll(String locale, String currency, Set<String> productCodes);
	
}

 