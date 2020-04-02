package io.nzbee.entity.product.currency;

import java.util.Optional;

public interface ICurrencyService {

	Optional<Currency> findById(Long Id);

	Optional<Currency> findByCode(String code);

}
