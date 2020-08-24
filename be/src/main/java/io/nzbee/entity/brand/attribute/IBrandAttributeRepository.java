package io.nzbee.entity.brand.attribute;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface IBrandAttributeRepository extends CrudRepository<BrandAttribute, Long> {

	List<BrandAttribute> findAll();

	List<BrandAttribute> findByLclCd(String lcl);

	Optional<BrandAttribute> findByLclCdAndBrandBrandId(String lcl, Long id);
	
	Optional<BrandAttribute> findByLclCdAndBrandBrandCode(String lcl, String code);

}
