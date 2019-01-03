package io.javabrains.springbootstarter.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository; 
	
	@Autowired
	private ProductCategoryRepository productCategoryRepository;
	
	public List<Product> getAllProducts() {
		List<Product> Products = new ArrayList<>();
		Iterator<Product> i = productRepository.findAll().iterator();
		while(i.hasNext()) {
			  Products.add(i.next());
		}
		return Products;
	}
	
	public List<Product> getAllProducts(String lcl) {
		List<Product> Products = new ArrayList<>();
		Iterator<Product> i = productRepository.findByLclCd(lcl).iterator();
		while(i.hasNext()) {
			  Products.add(i.next());
		}
		return Products;
	}
	
	public List<Product> getAllProducts(String lcl, Long productCategoryId) {
		//here we need to recursively loop through the sub-categories
		//of the passed category and build a list of de-duplicated products
		Optional<ProductCategory> cat = productCategoryRepository.findByLclCdAndCategoryId(lcl, productCategoryId);
		List<Product> pal = new ArrayList<Product>();
		recurseCategories(cat.get(), pal);
//		for(Product p : pal) {
//            System.out.println(p.getProductDesc());
//        }
		return pal;
	}
	
	private void recurseCategories(ProductCategory cat, List<Product> pal) {
		if(cat.getChildren().isEmpty()) {
			//System.out.println("there are no children for this category " + cat.getCategoryDesc());
			pal.addAll(cat.getProducts());
			return;
		}
		//System.out.println("there are children for this category " + cat.getCategoryDesc());
		for(ProductCategory c : cat.getChildren()) {
			recurseCategories(c, pal);
		}
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
