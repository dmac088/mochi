package io.nzbee.domain.brand;

import java.util.Optional;
import java.util.Set;
import io.nzbee.domain.ILocalizedService;

public interface IBrandService extends ILocalizedService<Brand> {
	
	Set<Brand> findAll(String locale, String currency, String category);
	
	Optional<Brand> findByProductCode(String locale, String currency, String productCode);
	
}
