package io.nzbee.entity.product.attribute;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import io.nzbee.entity.ILocalizedDao;

public interface IProductAttributeService extends ILocalizedDao<ProductAttribute> {
	
	Optional<ProductAttribute> getProductAttributeEN(Long id);
	
	Optional<ProductAttribute> getProductAttributeHK(Long id);

	Optional<ProductAttribute> getProductAttribute(Long id, String locale);

	Optional<ProductAttribute> findById(String locale, String currency, long id);

	List<ProductAttribute> findAll(String locale, String currency);

	Optional<ProductAttribute> findByDesc(String locale, String currency, String desc);

	List<ProductAttribute> findAll(String locale, String currency, Set<String> codes);
}
