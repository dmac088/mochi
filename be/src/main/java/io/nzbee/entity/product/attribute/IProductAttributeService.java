package io.nzbee.entity.product.attribute;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import io.nzbee.entity.ILocalizedDao;

public interface IProductAttributeService extends ILocalizedDao<ProductAttributeDTO, ProductAttributeEntity> {
	
	Optional<ProductAttributeEntity> getProductAttributeEN(Long id);
	
	Optional<ProductAttributeEntity> getProductAttributeHK(Long id);

	Optional<ProductAttributeEntity> getProductAttribute(Long id, String locale);

	Optional<ProductAttributeEntity> findById(String locale, String currency, Long id);

	List<ProductAttributeEntity> findAll(String locale, String currency);

	Optional<ProductAttributeEntity> findByDesc(String locale, String currency, String desc);

	List<ProductAttributeEntity> findAll(String locale, String currency, Set<String> codes);

}
