package io.javabrains.springbootstarter.services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.javabrains.springbootstarter.facets.CategoryFacet;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private IProductService productService;

    public ProductController() {
        super();
    }
    
    @GetMapping("/Product/{lcl}/{curr}/id/{id}")
    public Product getProduct(@PathVariable String lcl, @PathVariable String curr, @PathVariable Long id) {
    	return productService.getProduct(lcl, curr, id);
    }
    
    @GetMapping("/Product/{lcl}/{curr}/page/{page}/size/{size}/sortBy/{sortBy}")
    public ResultContainer getProducts(@PathVariable String lcl, @PathVariable String curr, @PathVariable int page, @PathVariable int size, @PathVariable String sortBy) {
    	return productService.getProducts(lcl, curr, page, size, sortBy);
    }
    
    @GetMapping("/Product/{lcl}/{curr}/categoryId/{cat}/preview")
    public List<Product> getProducts(@PathVariable String lcl, @PathVariable String curr, @PathVariable Long cat) {
    	return productService.getPreviewProductsForCategory(lcl, curr, cat);
    }
   
    @GetMapping("/Product/{lcl}/{curr}/categoryDesc/{category}/page/{page}/size/{size}/sortBy/{sortBy}")
    public ResultContainer getProducts(@PathVariable String lcl, @PathVariable String curr, @PathVariable String category, @PathVariable int page, @PathVariable int size, @PathVariable String sortBy) {
    	return productService.getProductsForCategory(lcl, curr, category, page, size, sortBy);
    }
    
    @GetMapping("/Product/{lcl}/{curr}/categoryDesc/{category}/brand/{brand}/page/{page}/size/{size}/sortBy/{sortBy}")
    public ResultContainer getProducts(@PathVariable String lcl, @PathVariable String curr, @PathVariable String category, @PathVariable String brand, @PathVariable int page, @PathVariable int size, @PathVariable String sortBy) {
    	return productService.getProductsForCategoryAndBrand(lcl, curr, category, brand, page, size, sortBy);
    }
    
    @GetMapping("/Product/{lcl}/{curr}/categoryDesc/{category}/maxPrice/{price}/page/{page}/size/{size}/sortBy/{sortBy}")
    public ResultContainer getProducts(@PathVariable String lcl, @PathVariable String curr, @PathVariable String category, @PathVariable Double price, @PathVariable int page, @PathVariable int size, @PathVariable String sortBy) {
    	return productService.getProductsForCategoryAndPrice(lcl, curr, category, price, page, size, sortBy);
    }
    
    @GetMapping("/Product/{lcl}/{curr}/categoryDesc/{category}/brand/{brand}/maxPrice/{price}/page/{page}/size/{size}/sortBy/{sortBy}")
    public ResultContainer getProducts(@PathVariable String lcl, @PathVariable String curr, @PathVariable String category, @PathVariable String brand, @PathVariable Double price, @PathVariable int page, @PathVariable int size, @PathVariable String sortBy) {
    	return productService.getProductsForCategoryAndBrandAndPrice(lcl, curr, category, brand, price, page, size, sortBy);
    }
    
    @PostMapping("/Search/{lcl}/{curr}/Category/{categoryCode}/SearchTerm/{term}/Page/{page}/Size/{size}/SortBy/{sortBy}")
    public ResultContainer search(@PathVariable String lcl, @PathVariable String curr, @PathVariable String categoryCode,@PathVariable String term, @PathVariable int page, @PathVariable int size, @PathVariable String sortBy, @RequestBody final CategoryFacet[] selectedFacets) {
    	if(selectedFacets.length > 0) {
    		System.out.println("Facet Name = " + selectedFacets[0].getFieldName());
    	}
    	//System.out.println(facets[1].getFieldName());
    	return productService.findProduct(lcl, curr, categoryCode, term, page, size, sortBy, selectedFacets);
    }
}
