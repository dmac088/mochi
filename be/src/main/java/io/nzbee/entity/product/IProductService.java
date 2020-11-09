package io.nzbee.entity.product;

import java.util.Optional;
import java.util.Set;
import org.springframework.data.domain.Page;
import io.nzbee.entity.ILocalizedService;

public interface IProductService extends ILocalizedService<ProductDTO, ProductEntity> {
	
	<T> Set<ProductDTO> findAllByType(String locale, String currency, Class<T> cls);

	Page<ProductDTO> findAll(String locale, String currency, String categoryCode, Set<String> categoryCodes,
			Set<String> brandCodes, Set<String> tagCodes, Double maxPrice, String page, String size, String sort);

	Optional<ProductDTO> findByCode(String locale, String currency, String code);

	Optional<ProductDTO> findById(String locale, String currency, long id);

	Optional<ProductDTO> findByDesc(String locale, String currency, String desc);

	Set<ProductDTO> findAll(String locale, String currency);

	Set<ProductDTO> findAll(String locale, String currency, Set<String> productCodes);

	Optional<ProductEntity> findByCode(String productUPC);

	<T> Set<ProductEntity> findAllByType(Class<T> cls);
 
	void save(String locale, String currency, ProductEntity product);
}
