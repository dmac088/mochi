package io.nzbee.entity.product;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "productEntityService")
public class ProductService {

	@Autowired
	private ProductRepository productRepository; 
	
	public List<Product> getProducts() {
		return productRepository.findAll();
	}

//	public List<Product> getProduct(Long productId) {
//		return 
//	}

}