package io.javabrains.springbootstarter.product_lcl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductLclService {

	@Autowired
	private ProductLclRepository productLclRepository; 
	
	public List<ProductLcl> getAllProductLcls() {
		List<ProductLcl> Products = new ArrayList<>();
		Iterator<ProductLcl> i = productLclRepository.findAll().iterator();
		while(i.hasNext()) {
			  Products.add(i.next());
		}
		return Products;
	}
	
	public ProductLcl getProductLcl(Long id) {
		ProductLcl p = productLclRepository.findOne(id);
		return p;
	}
	
	public String getProductImageLcl(Long id) {
		ProductLcl p = productLclRepository.findOne(id);
		return p.getProductImage();
	}
	
	public void addProductLcl(ProductLcl productLcl) {
		productLclRepository.save(productLcl);
	}
	
	public void updateProductLcl(ProductLcl productLcl) {
		productLclRepository.save(productLcl);
	}
	
	public void deleteProductLcl(Long id) {
		productLclRepository.delete(id);
	}
	
}
