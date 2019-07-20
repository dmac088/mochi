package io.nzbee.entity.product;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.nzbee.variables.GeneralVars;

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
	
	public Optional<ProductAttribute> getProductAttribute(Long id, String lcl) {
		return productAttributeRepository.findByLclCdAndProductId(lcl, id);
	}
	
	public Optional<ProductAttribute> getProductAttributeEN(Long id) {
		return productAttributeRepository.findByLclCdAndProductId(GeneralVars.LANGUAGE_ENGLISH, id);
	}
	
	public Optional<ProductAttribute> getProductAttributeHK(Long id) {
		return productAttributeRepository.findByLclCdAndProductId(GeneralVars.LANGUAGE_HK, id);
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
