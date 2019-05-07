package io.javabrains.springbootstarter.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.javabrains.springbootstarter.domain.Product;
import io.javabrains.springbootstarter.dto.SearchDTO;
import io.javabrains.springbootstarter.dto.SidebarFacetDTO;
import io.javabrains.springbootstarter.services.IProductService;

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
    
    @PostMapping("/Product/{lcl}/maxprice")
    public Double getMaxPrice(@PathVariable String lcl, @PathVariable String curr, @RequestBody final List<SidebarFacetDTO> selectedFacets) {
    	return productService.getMaxPrice(lcl, curr, selectedFacets);
    }
    
    @GetMapping("/Product/{lcl}/{curr}/page/{page}/size/{size}/sortBy/{sortBy}")
    public SearchDTO getProducts(@PathVariable String lcl, @PathVariable String curr, @PathVariable int page, @PathVariable int size, @PathVariable String sortBy) {
    	return productService.getProducts(lcl, curr, page, size, sortBy);
    }
    
    @GetMapping("/Product/{lcl}/{curr}/categoryId/{cat}/preview")
    public List<Product> getProducts(@PathVariable String lcl, @PathVariable String curr, @PathVariable Long cat) {
    	return productService.getPreviewProductsForCategory(lcl, curr, cat);
    }
   
    @GetMapping("/Product/{lcl}/{curr}/category/{category}/page/{page}/size/{size}/sortBy/{sortBy}")
    public SearchDTO getProducts(@PathVariable String lcl, @PathVariable String curr, @PathVariable String category, @PathVariable int page, @PathVariable int size, @PathVariable String sortBy) {
    	return productService.getProductsForCategory(lcl, curr, category, page, size, sortBy);
    }
    
    @GetMapping("/Product/{lcl}/{curr}/category/{category}/brand/{brand}/page/{page}/size/{size}/sortBy/{sortBy}")
    public SearchDTO getProducts(@PathVariable String lcl, @PathVariable String curr, @PathVariable String category, @PathVariable String brand, @PathVariable int page, @PathVariable int size, @PathVariable String sortBy) {
    	return productService.getProductsForCategoryAndBrand(lcl, curr, category, brand, page, size, sortBy);
    }
    
    @PostMapping("/Product/{lcl}/{curr}/category/{category}/maxPrice/{price}/page/{page}/size/{size}/sortBy/{sortBy}")
    public SearchDTO getProducts(@PathVariable String lcl, @PathVariable String curr, @PathVariable String category, @PathVariable Double price, @PathVariable int page, @PathVariable int size, @PathVariable String sortBy, @RequestBody final List<SidebarFacetDTO> selectedFacets) {
    	return productService.getProductsForCategoryAndBrandAndPrice(lcl, curr, category, price, page, size, sortBy, selectedFacets);
    }
    
    @PostMapping("/Search/{lcl}/{curr}/Category/{category}/SearchTerm/{term}/Page/{page}/Size/{size}/SortBy/{sortBy}")
    public SearchDTO search(@PathVariable String lcl, @PathVariable String curr, @PathVariable String category,@PathVariable String term, @PathVariable int page, @PathVariable int size, @PathVariable String sortBy, @RequestBody final List<SidebarFacetDTO> selectedFacets) {
    	return productService.findProduct(lcl, curr, category, term, page, size, sortBy, selectedFacets);
    }
}
