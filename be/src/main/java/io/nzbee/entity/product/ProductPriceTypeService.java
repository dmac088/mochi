package io.nzbee.entity.product;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProductPriceTypeService {

	@Autowired
	private ProductPriceTypeRepository productPriceTypeRepository; 
	
	public List<ProductPriceType> getProductPriceTypes() {
		return productPriceTypeRepository.findAll();
	}

	public Optional<ProductPriceType> getProductPriceType(Long Id) {
		return productPriceTypeRepository.findById(Id);
	}
}
