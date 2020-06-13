package io.nzbee.entity.product.price;


import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface IProductPriceRepository extends CrudRepository<ProductPrice, Long> {

	List<ProductPrice> findAll();
	
	Optional<ProductPrice> findByTypeCodeAndProductProductUPCAndCurrencyCode(String code, String upcCode, String currency);
}