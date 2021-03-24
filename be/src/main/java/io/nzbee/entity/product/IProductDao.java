package io.nzbee.entity.product;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import io.nzbee.entity.ILocalizedDao;
import io.nzbee.entity.StringCollectionWrapper;

public interface IProductDao extends ILocalizedDao<ProductDTO, ProductEntity> {
	
	Page<ProductDTO> findAll(	String locale, 
								String currency, 
								String rootCategory,
								Pageable pageable,
								String orderby);

	Optional<ProductEntity> findByCode(String productUPC);
	
	<T> List<ProductDTO> findAllByType(String locale, String currency, String rootCategory, Class<T> cls);

	Optional<ProductDTO> findByCode(String locale, String currency, String code);

	Optional<ProductDTO> findByDesc(String locale, String currency, String desc);

	List<ProductDTO> findAll(String locale, String rootCategory, StringCollectionWrapper codes);

	List<ProductDTO> findAll(String locale, String currency, String rootCategory, StringCollectionWrapper productCodes);

	Optional<ProductDTO> findById(String locale, String currency, Long productId);

	<T> Page<ProductDTO> findAll(String locale, String currency, String categoryCode, StringCollectionWrapper categoryCodes,
			StringCollectionWrapper brandCodes, StringCollectionWrapper tagCodes, Double maxPrice,
			Class<T> cls, String page, String size, String sort);

	Page<ProductDTO> findAll(String locale, String currency, String categoryCode, StringCollectionWrapper categoryCodes,
			StringCollectionWrapper brandCodes, StringCollectionWrapper tagCodes, Double maxPrice, String page,
			String size, String sort);

	Optional<ProductDTO> findShippingProductByDestinationAndTypeAndWeight(String locale, String currency,
			String destinationCode, String type, Double weightKg);
	
}
