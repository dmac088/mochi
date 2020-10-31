package io.nzbee.entity.product;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Page;
import io.nzbee.entity.ILocalizedService;

public interface IProductService extends ILocalizedService<ProductEntity> {
	
	<T> List<ProductEntity> findAllByType(String locale, String currency, Class<T> cls);

	Page<ProductEntity> findAll(String locale, String currency, String categoryCode, Set<String> categoryCodes,
			Set<String> brandCodes, Set<String> tagCodes, Double maxPrice, String page, String size, String sort);

	Optional<ProductEntity> findByCode(String locale, String currency, String code);

	Optional<ProductEntity> findById(String locale, String currency, long id);

	Optional<ProductEntity> findByDesc(String locale, String currency, String desc);

	List<ProductEntity> findAll(String locale, String currency);

	List<ProductEntity> findAll(String locale, String currency, Set<String> productCodes);

	Optional<ProductEntity> findByCode(String productUPC);

	<T> List<ProductEntity> findAllByType(Class<T> cls);
 
}
