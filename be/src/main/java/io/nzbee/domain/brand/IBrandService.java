package io.nzbee.domain.brand;

import java.util.Set;
import io.nzbee.domain.IDimensionService;

public interface IBrandService extends IDimensionService<Brand> {
	
	Set<Brand> findAll(String locale, String currency, String category);
	
}
