package io.nzbee.entity.brand.attribute;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface BrandAttributeRepository extends CrudRepository<BrandAttribute, Long> {

	List<BrandAttribute> findAll();

	List<BrandAttribute> findByLclCd(String lcl);
	
	Optional<BrandAttribute> findByBrandIdAndLclCd(Long Id, String lcl);
	
	Optional<BrandAttribute> findByLclCdAndBrandProductsProductId(String lcl, Long productId);

}
