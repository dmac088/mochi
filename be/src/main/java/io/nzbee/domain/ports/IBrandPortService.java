package io.nzbee.domain.ports;

import java.util.Set;
import io.nzbee.domain.brand.Brand;

public interface IBrandPortService  extends IProductDimensionService<Brand> {

	Set<Brand> findAll(String locale, String currency, String category);

	Brand findByProductCode(String locale, String currency, String productCode);

	Set<Brand> findAll(String locale, String currency, String categoryCode, Set<String> categoryCodes,
			Set<String> tagCodes, Double maxPrice);

}
