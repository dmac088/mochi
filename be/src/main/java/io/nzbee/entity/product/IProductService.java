package io.nzbee.entity.product;

import java.util.List;
import java.util.Optional;
import io.nzbee.entity.ILocalizedService;
import io.nzbee.entity.StringCollectionWrapper;

public interface IProductService extends ILocalizedService<ProductDTO, ProductEntity> {
	
	<T> List<ProductDTO> findAllByType(String locale, String currency, String rootCategory, Class<T> cls);

	Optional<ProductDTO> findByCode(String locale, String currency, String code);

	Optional<ProductDTO> findByDesc(String locale, String currency, String desc);

	List<ProductDTO> findAll(String locale, String currency, String rootCategory, StringCollectionWrapper productCodes);

	Optional<ProductEntity> findByCode(String productUPC);
 
	void save(String locale, String currency, ProductEntity product);

	Optional<ProductDTO> findById(String locale, String currency, Long productId);

}
