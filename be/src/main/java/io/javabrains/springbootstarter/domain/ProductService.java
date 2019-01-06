package io.javabrains.springbootstarter.domain;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository; 
	
	@Autowired
	private ProductPagingAndSortingRepository productPagingAndSortingRepository;
	
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}
	
	public Page<Product> getAllProducts(int page, int size) {
		return productPagingAndSortingRepository.findAll(PageRequest.of(page, size));
	}
	
	public Optional<Product> getProduct(Long id) {
		Optional<Product> p = productRepository.findById(id);
		return p;
	}
	
	public void addProduct(Product product) {
		productRepository.save(product);
	}
	
	public void updateProduct(Long id, Product product) {
		productRepository.save(product);
	}
	
	public void deleteProduct(Long id) {
		productRepository.deleteById(id);
	}
	
}
