package io.nzbee.entity.brand;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface BrandAttributeRepository extends CrudRepository<BrandAttribute, Long> {

	List<BrandAttribute> findAll();

	List<BrandAttribute> findByLclCd(String lcl);
	
	BrandAttribute findByBrandIdAndLclCd(Long Id, String lcl);
	
	BrandAttribute findByLclCdAndBrandProductsProductId(String lcl, Long productId);

}
