package io.nzbee.entity.product;

import java.util.Optional;
import java.util.Set;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import io.nzbee.entity.ILocalizedDao;

public interface IProductDao extends ILocalizedDao<ProductDTO, ProductEntity> {
	
	Page<ProductDTO> findAll(	String locale, 
							String currency, 
							Pageable pageable,
							String orderby);

	<T> Set<ProductDTO> findAllByType(String locale, String currency, Class<T> cls);


	Page<ProductDTO> findAll(String locale, String currency, String categoryCode, Set<String> categoryCodes,
			Set<String> brandCodes, Set<String> tagCodes, Double maxPrice, String page, String size, String sort);

	Optional<ProductDTO> findByCode(String locale, String currency, String code);

	Optional<ProductDTO> findByDesc(String locale, String currency, String desc);

	Set<ProductDTO> findAll(String locale, String currency);

	Set<ProductDTO> findAll(String locale, String currency, Set<String> codes);

	Optional<ProductEntity> findByCode(String productUPC);

	Optional<ProductDTO> findById(String locale, String currency, Long productId);

	
}
