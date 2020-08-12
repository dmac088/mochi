package io.nzbee.entity.brand.attribute;

import java.util.Optional;

import io.nzbee.entity.ILocalizedDao;

public interface IBrandAttributeService extends ILocalizedDao<BrandAttribute> {
	
	Optional<BrandAttribute> getBrandAttributeEN(Long id);
	
	Optional<BrandAttribute> getBrandAttributeHK(Long id);

	Optional<BrandAttribute> getBrandAttribute(Long id, String locale);


}
