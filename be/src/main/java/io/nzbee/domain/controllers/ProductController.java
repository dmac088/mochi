package io.nzbee.domain.controllers;
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
    
}
