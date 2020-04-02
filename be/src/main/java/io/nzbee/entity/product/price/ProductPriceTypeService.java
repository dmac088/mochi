package io.nzbee.entity.product.price;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductPriceTypeService implements IProductPriceTypeService {

	@Autowired
	private ProductPriceTypeRepository productPriceTypeRepository; 
	
	@Override
	public List<ProductPriceType> findAll() {
		return productPriceTypeRepository.findAll();
	}

	@Override
	public Optional<ProductPriceType> findById(Long Id) {
		return productPriceTypeRepository.findById(Id);
	}
	
	@Override
	public Optional<ProductPriceType> findByCode(String code) {
		return productPriceTypeRepository.findByCode(code);
	}

}
