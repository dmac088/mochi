package io.javabrains.springbootstarter.domain;


import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ProductPriceRepository extends CrudRepository<ProductPrice, Long> {

	List<ProductPrice> findAll();
	
	ProductPrice findByid(Long id);
	 
	//ProductPrice findByProductProductIdAndTypePriceTypeDescAndStartDateLessThanEqualAndEndDateGreaterThanEqualAndCurrencyCurrencyCode(Long productId, String priceTypeDesc, Date priceStart, Date priceEnd, String currencyCode);
}