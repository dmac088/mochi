package io.nzbee.entity.brand.attribute;

import java.util.Optional;

import io.nzbee.entity.ILocalizedDao;

public interface IBrandAttributeService extends ILocalizedDao<BrandAttributeDTO, BrandAttributeEntity> {
	
	Optional<BrandAttributeEntity> getBrandAttributeEN(Long id);
	
	Optional<BrandAttributeEntity> getBrandAttributeHK(Long id);

	Optional<BrandAttributeEntity> getBrandAttribute(Long id, String locale);

	Optional<BrandAttributeEntity> findById(long id);


}
