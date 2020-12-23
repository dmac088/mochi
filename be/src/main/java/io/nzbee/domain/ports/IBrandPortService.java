package io.nzbee.domain.ports;

import java.util.List;
import java.util.Set;
import io.nzbee.domain.brand.Brand;

public interface IBrandPortService extends IProductDimensionService<Brand> {

	List<Brand> findAll(String locale, String category);

	Brand findByProductCode(String locale, String productCode);

	List<Brand> findAll(String locale, String currency, String categoryCode, Set<String> categoryCodes,
			Set<String> tagCodes, Double maxPrice);


}
