package io.nzbee.entity.product;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "productEntityService")
public class ProductService {

	@Autowired
	private ProductRepository productRepository; 
	
	public List<Product> getProducts() {
		return productRepository.findAll();
	}

	public Optional<Product> getProduct(Long Id) {
		return productRepository.findById(Id);
	}
	
	public Optional<Product> getProduct(String upc) {
		return productRepository.findByUPC(upc);
	}

}