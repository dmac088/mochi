package io.nzbee.entity.product.price;


import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface ProductPriceRepository extends CrudRepository<ProductPrice, Long> {

	List<ProductPrice> findAll();

	Optional<ProductPrice> findByProductProductIdAndTypeDescAndStartDateLessThanEqualAndEndDateGreaterThanEqualAndCurrencyCode(Long productId, String priceTypeDesc, Date priceStart, Date priceEnd, String currencyCode);

	Optional<ProductPrice> findByProductProductIdAndTypeCodeAndStartDateLessThanEqualAndEndDateGreaterThanEqualAndCurrencyCode(
			Long productId, String priceTypeCode, Date priceDateA, Date priceDateB, String currencyCode);
}