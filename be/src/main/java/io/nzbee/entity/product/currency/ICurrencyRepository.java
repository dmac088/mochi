package io.nzbee.entity.product.currency;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface ICurrencyRepository extends CrudRepository<Currency, Long> {
	
	Optional<Currency> findByCode(String code); 
	
}
