package io.nzbee.entity.product;


import java.util.Date;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface ProductPriceRepository extends CrudRepository<ProductPrice, Long> {

	List<ProductPrice> findAll();
	
	ProductPrice findByid(Long id);
	
	ProductPrice findByProductProductIdAndTypeDescAndStartDateLessThanEqualAndEndDateGreaterThanEqualAndCurrencyCode(Long productId, String priceTypeDesc, Date priceStart, Date priceEnd, String currencyCode);

	ProductPrice findByProductProductIdAndTypeCodeAndStartDateLessThanEqualAndEndDateGreaterThanEqualAndCurrencyCode(
			Long productId, String priceTypeCode, Date priceDateA, Date priceDateB, String currencyCode);
}