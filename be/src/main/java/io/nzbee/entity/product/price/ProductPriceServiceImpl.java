package io.nzbee.entity.product.price;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductPriceServiceImpl implements IProductPriceService {

	@Autowired
	private IProductPriceRepository productPriceRepository; 
	
	@Override
	public Optional<ProductPrice> findOne(Long productId, String priceTypeCode, String currencyCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<ProductPrice> findOne(String productCode, String priceTypeCode, String currencyCode) {
		return productPriceRepository.findByTypeCodeAndProductProductUPCAndCurrencyCode(priceTypeCode, productCode, currencyCode);
	}
	
	@Override
	public Optional<ProductPrice> findById(String locale, String currency, long id) {
		return productPriceRepository.findById(id);
	}

	@Override
	public List<ProductPrice> findAll(String locale, String currency) {
		return productPriceRepository.findAll();
	}
	

	@Override
	public List<ProductPrice> findAll(String locale, String currency, Set<String> codes) {
		return productPriceRepository.findAll();
	}
	
	@Override
	public Optional<ProductPrice> findByCode(String locale, String currency, String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<ProductPrice> findByDesc(String locale, String currency, String desc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(ProductPrice t) {
		productPriceRepository.save(t);
	}

	@Override
	public void update(ProductPrice t) {
		productPriceRepository.save(t);
	}

	@Override
	public void delete(ProductPrice t) {
		productPriceRepository.delete(t);
	}

	@Override
	public List<ProductPrice> findAll(String locale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductPrice> findAll(String locale, Set<String> codes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<ProductPrice> findById(String locale, long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<ProductPrice> findByCode(String locale, String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<ProductPrice> findByDesc(String locale, String desc) {
		// TODO Auto-generated method stub
		return null;
	}

}
