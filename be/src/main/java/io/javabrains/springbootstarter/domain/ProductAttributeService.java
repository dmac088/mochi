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

	public List<ProductAttribute> getProductAttribute(String lcl) {
		return productAttributeRepository.findByLclCd(lcl);
	}
	
	public Optional<ProductAttribute> getProductAttribute(String lcl, Long id) {
		return productAttributeRepository.findByLclCdAndProductId(lcl, id);
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
