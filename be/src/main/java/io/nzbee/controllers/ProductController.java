package io.nzbee.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.nzbee.domain.Product;
import io.nzbee.domain.services.product.IProductService;
import io.nzbee.ui.component.web.search.SearchDto;
import io.nzbee.ui.component.web.sidebar.Sidebar;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private IProductService productService;

    public ProductController() {
        super();
    }
    
    @GetMapping("/Product/{locale}/{currency}/id/{id}")
    public Product getProduct(@PathVariable String locale, @PathVariable String currency, @PathVariable Long id) {
    	return productService.findOne(locale, currency, id);
    }
    
    @PostMapping("/Product/{locale}/{currency}")
    public List<Product> getProducts(@PathVariable String locale, @PathVariable String currency, @RequestBody final List<Long> productIds) {
    	return productService.findAll(locale, currency, productIds);
    }
    
    @PostMapping("/Product/{locale}/{currency}/{category}/maxprice")
    public Double getMaxPrice(@PathVariable String locale, @PathVariable String currency, @PathVariable String category, @RequestBody final List<Sidebar> selectedFacets) {
    	return productService.getMaxPrice(category, locale, currency, selectedFacets);
    }
    
//    @PostMapping("/Product/{locale}/{currency}/category/{category}/maxPrice/{price}/page/{page}/size/{size}/sortBy/{sortBy}")
//    public SearchDto getProducts(@PathVariable String locale, @PathVariable String currency, @PathVariable String category, @PathVariable Double price, @PathVariable int page, @PathVariable int size, @PathVariable String sortBy, @RequestBody final List<Sidebar> selectedFacets) {
//    	return productService.findAll(locale, currency, category, price, page, size, sortBy, selectedFacets);
//    }
// 
//    @PostMapping("/Search/{locale}/{currency}/Category/{category}/SearchTerm/{term}/Page/{page}/Size/{size}/SortBy/{sortBy}")
//    public SearchDto search(@PathVariable String locale, @PathVariable String currency, @PathVariable String category,@PathVariable String term, @PathVariable int page, @PathVariable int size, @PathVariable String sortBy, @RequestBody final List<Sidebar> selectedFacets) {
//    	return productService.findAll(locale, currency, category, term, page, size, sortBy, selectedFacets);
//    }
    
}
