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
	public Optional<ProductPriceEntity> findOne(Long productId, String priceTypeCode, String currencyCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<ProductPriceEntity> findOne(String productCode, String priceTypeCode, String currencyCode) {
		return productPriceRepository.findByTypeCodeAndProductProductUPCAndCurrencyCode(priceTypeCode, productCode, currencyCode);
	}
	
	@Override
	public Optional<ProductPriceEntity> findById(String locale, String currency, Long id) {
		return productPriceRepository.findById(id);
	}

	@Override
	public List<ProductPriceEntity> findAll(String locale, String currency) {
		return productPriceRepository.findAll();
	}
	

	@Override
	public List<ProductPriceEntity> findAll(String locale, String currency, Set<String> codes) {
		return productPriceRepository.findAll();
	}
	
	@Override
	public Optional<ProductPriceEntity> findByCode(String locale, String currency, String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<ProductPriceEntity> findByDesc(String locale, String currency, String desc) {
		// TODO Auto-generated method stub
		return null;
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

	@Override
	public List<ProductPriceDTO> findAll(String locale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductPriceDTO> findAll(String locale, Set<String> codes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<ProductPriceDTO> findById(String locale, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<ProductPriceDTO> findDTOByCode(String locale, String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<ProductPriceDTO> findByDesc(String locale, String desc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductPriceEntity> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<ProductPriceEntity> findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<ProductPriceEntity> findByCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductPriceEntity> findAll(Set<String> codes) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Optional<ProductPriceEntity> findEntityByCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<ProductPriceEntity> findEntityByDesc(String locale, String desc) {
		// TODO Auto-generated method stub
		return null;
	}

}
