package io.nzbee.entity.product;

import java.util.List;
import java.util.Optional;
import io.nzbee.entity.IService;
import io.nzbee.entity.StringCollectionWrapper;

public interface IProductService extends IService<ProductEntity> {
	
	Optional<ProductEntity> findByCode(String productUPC);
	
	void save(String locale, String currency, ProductEntity product);
	
	<T> List<ProductDTO> findAllByType(String locale, String currency, String rootCategory, Class<T> cls);

	Optional<ProductDTO> findById(String locale, String currency, Long productId);
	
	Optional<ProductDTO> findByCode(String locale, String currency, String code);

	Optional<ProductDTO> findByDesc(String locale, String currency, String desc);

	List<ProductDTO> findAll(String locale, String currency, String rootCategory, StringCollectionWrapper productCodes);

	Optional<ProductEntity> findById(Long id);

	Optional<ProductEntity> findEntityByCode(String productUPC);

}
