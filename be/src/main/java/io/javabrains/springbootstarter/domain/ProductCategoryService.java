package io.javabrains.springbootstarter.domain;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductCategoryService {

	@Autowired
	private ProductCategoryRepository productCategoryRepository; 
	
	@Autowired
	private ProductCategoryAttributeRepository productCategoryAttributeRepository; 
	
	public List<ProductCategoryAttribute> getAllProductCategories(String lcl) {
		return productCategoryAttributeRepository.findByLclCd(lcl);
//		List<ProductCategory> categories = new ArrayList<>();
//		Iterator<ProductCategory> i = productCategoryRepository.findByProductCategoryAttributeLclCd(lcl).iterator();
//		while(i.hasNext()) {
//			categories.add(i.next());
//		}
//		return categories;
	}
	
//	public Optional<ProductCategory> getProductCategory(String lcl, Long id) {
//		Optional<ProductCategory> p = productCategoryRepository.findAll();//(lcl, id);
//		return p;
//	}
	
	public void addProductCategory(ProductCategory productCategory) {
		productCategoryRepository.save(productCategory);
	}
	
	public void updateProductCategory(Long id, ProductCategory productCategory) {
		productCategoryRepository.save(productCategory);
	}
	
	public void deleteProductCategory(Long id) {
		productCategoryRepository.deleteById(id);
	}
	
}
