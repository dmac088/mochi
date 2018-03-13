package io.javabrains.springbootstarter.product;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository; 
	
	public List<Product> getAllProducts() {
		List<Product> Products = new ArrayList<>();
		Iterator<Product> i = productRepository.findAll().iterator();
		while(i.hasNext()) {
			  Products.add(i.next());
		}
		return Products;
	}
	
	public Product getProduct(Long id) {
		Product p = productRepository.findOne(id);
		return p;
	}
	
	public String getProductImage(Long id) {
		Product p = productRepository.findOne(id);
		return p.getProductImage();
	}
	
}
