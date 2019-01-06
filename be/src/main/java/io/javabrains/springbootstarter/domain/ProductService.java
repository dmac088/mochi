package io.javabrains.springbootstarter.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.beans.support.SortDefinition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository; 
	
	@Autowired
	private ProductPagingAndSortingRepository productPagingAndSortingRepository;
	
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
	
	public Page<Product> getAllProducts(String lcl, int page, int size) {
	
		return productPagingAndSortingRepository.findByLclCd(lcl, PageRequest.of(page, size, Sort.by("productRrp")));
	}
	
	public Page<Product> getAllProducts(String lcl, Long productCategoryId, int page, int size) {
		return null;
		//return productPagingAndSortingRepository
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
