package io.javabrains.springbootstarter.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductAttributeService {

	@Autowired
	private ProductAttributeRepository productAttributeRepository; 
	
	public List<ProductAttribute> getProductAttribute() {
		return productAttributeRepository.findAll();
	}
	
	public Optional<ProductAttribute> getProductAttribute(Long id) {
		return productAttributeRepository.findByProductId(id);
	}
	
	public void addProductAttribute(ProductAttribute ProductAttribute) {
		productAttributeRepository.save(ProductAttribute);
	}
	
	public void updateProductAttribute(Long id, ProductAttribute ProductAttribute) {
		productAttributeRepository.save(ProductAttribute);
	}
	
	public void deleteProductAttribute(Long id) {
		productAttributeRepository.deleteById(id);
	}
	
}
