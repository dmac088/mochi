package io.nzbee.entity.product;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Page;
import io.nzbee.entity.ILocalizedService;

public interface IProductService extends ILocalizedService<Product> {
	
	<T> List<Product> findAllByType(String locale, String currency, Class<T> cls);

	Page<Product> findAll(String locale, String currency, String categoryCode, Set<String> categoryCodes,
			Set<String> brandCodes, Set<String> tagCodes, Double maxPrice, String page, String size, String sort);

	Optional<Product> findByCode(String locale, String currency, String code);

	Optional<Product> findById(String locale, String currency, long id);

	Optional<Product> findByDesc(String locale, String currency, String desc);

	List<Product> findAll(String locale, String currency);

	List<Product> findAll(String locale, String currency, Set<String> productCodes);

	Optional<Product> findByCode(String productUPC);
 
}