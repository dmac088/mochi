package io.nzbee.entity.product.price;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProductPriceServiceImpl implements IProductPriceService {

	@Autowired
	private IProductPriceRepository productPriceRepository; 
	
	@Override
	public Optional<ProductPriceEntity> findByProductId(Long productId, String priceTypeCode, String currencyCode) {
		return productPriceRepository.findByTypeCodeAndProductProductIdAndCurrencyCode(priceTypeCode, productId, currencyCode);
	}

	@Override
	public Optional<ProductPriceEntity> findByProductCode(String productCode, String priceTypeCode, String currencyCode) {
		return productPriceRepository.findByTypeCodeAndProductProductUPCAndCurrencyCode(priceTypeCode, productCode, currencyCode);
	}
	
	@Override
	public Optional<ProductPriceEntity> findById(String locale, String currency, Long id) {
		return productPriceRepository.findById(id);
	}

	@Override
	public void save(ProductPriceEntity t) {
		productPriceRepository.save(t);
	}

	@Override
	public void update(ProductPriceEntity t) {
		productPriceRepository.save(t);
	}

	@Override
	public void delete(ProductPriceEntity t) {
		productPriceRepository.delete(t);
	}


}
