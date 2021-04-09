package io.nzbee.entity.product.attribute;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductAttributeServiceImpl implements IProductAttributeService {

	@Autowired
	private IProductAttributeRepository productAttributeRepository; 
	
	@Override
	public Optional<ProductAttributeEntity> findById(Long id) {
		return productAttributeRepository.findById(id);
	}

	@Override
	public void save(ProductAttributeEntity t) {
		productAttributeRepository.save(t);
	}

	@Override
	public void update(ProductAttributeEntity t) {
		productAttributeRepository.save(t);
	}

	@Override
	public void delete(ProductAttributeEntity t) {
		productAttributeRepository.delete(t);		
	}

	@Override
	public Optional<ProductAttributeEntity> findByCode(String locale, String code) {
		return productAttributeRepository.findByLclCdAndProductProductUPC(locale, code);
	}

}
