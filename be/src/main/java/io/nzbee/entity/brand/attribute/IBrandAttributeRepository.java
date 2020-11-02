package io.nzbee.entity.brand.attribute;

import java.util.Optional;
import java.util.Set;
import org.springframework.data.repository.CrudRepository;

public interface IBrandAttributeRepository extends CrudRepository<BrandAttributeEntity, Long> {

	Set<BrandAttributeEntity> findAll();

	Set<BrandAttributeEntity> findByLclCd(String lcl);

	Optional<BrandAttributeEntity> findByLclCdAndBrandBrandId(String lcl, Long id);
	
	Optional<BrandAttributeEntity> findByLclCdAndBrandBrandCode(String lcl, String code);

}
