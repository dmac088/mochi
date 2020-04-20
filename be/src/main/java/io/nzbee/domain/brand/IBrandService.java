package io.nzbee.domain.brand;

import java.util.Set;
import io.nzbee.domain.IProductDimensionService;

public interface IBrandService extends IProductDimensionService<Brand> {
	
	Set<Brand> findAll(String locale, String currency, String category);
	
}
