package io.nzbee.entity.product.price;


import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface IProductPriceRepository extends CrudRepository<ProductPriceEntity, Long> {

	List<ProductPriceEntity> findAll();
	
	Optional<ProductPriceEntity> findByTypeCodeAndProductProductUPCAndCurrencyCode(String code, String upcCode, String currency);

	Optional<ProductPriceEntity> findByTypeCodeAndProductProductIdAndCurrencyCode(String priceTypeCode, Long productId,
			String currencyCode);
}