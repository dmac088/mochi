package io.nzbee.entity.product.currency;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrencyServiceImpl implements ICurrencyService{

	@Autowired
	private ICurrencyRepository currencyRepository; 

	@Override
	public List<Currency> findAll() {
		return currencyRepository.findAll();
	}
	
	@Override
	public Optional<Currency> findById(Long Id) {
		return currencyRepository.findById(Id);
	}

	@Override
	public Optional<Currency> findByCode(String code) {
		return currencyRepository.findByCode(code);
	}
	
}


