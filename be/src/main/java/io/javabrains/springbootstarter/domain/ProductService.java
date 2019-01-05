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
		//here we need to recursively loop through the sub-categories
		//of the passed category and build a list of de-duplicated products
		Optional<ProductCategory> cat = productCategoryRepository.findByLclCdAndCategoryId(lcl, productCategoryId);
		Set<Product> set = new HashSet<>();
		recurseCategories(lcl, cat.get(), set);
		List<Product> pal = new ArrayList<Product>();
		pal.addAll(set);
		PagedListHolder<Product> productPage = new PagedListHolder<Product>(pal);
		productPage.setPageSize(size);
		productPage.setPage(page);
		MutableSortDefinition x = new MutableSortDefinition("productRrp", true, true);
		productPage.setSort(x);
		productPage.resort();
		return new PageImpl<Product>(productPage.getPageList());
	}
	
	private void recurseCategories(String lcl, ProductCategory cat, Set<Product> pset) {
		if(cat.getChildren().isEmpty()) {
			//System.out.println("there are no children for this category " + cat.getCategoryDesc());
			pset.addAll(productRepository.findByLclCdAndCategoriesCategoryId(lcl, cat.getCategoryId()));
			return;
		}
		//System.out.println("there are children for this category " + cat.getCategoryDesc());
		for(ProductCategory c : cat.getChildren()) {
			recurseCategories(lcl, c, pset);
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
