package io.nzbee.domain.ports;

import java.util.List;
import io.nzbee.domain.product.Product;

public interface IProductPortService extends IDomainPortService<Product> {

	<T> List<Product> findAllByType(String locale, String currency, Class<T> cls);

	Product findByDesc(String locale, String currency, String desc);

	Product findByCode(String locale, String currency, String code);

	Product findByDestinationAndTypeAndWeight(String locale, String currency, String destinationCode,
			String type, Double weightKg);

}
