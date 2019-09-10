package io.nzbee.domain.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.nzbee.domain.product.IProductService;
import io.nzbee.domain.product.Product;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private IProductService productService;
    
    @GetMapping("/Product/{locale}/{currency}/code/{code}")
    public Product getProduct(@PathVariable String locale, @PathVariable String currency, @PathVariable String code) {
    	return productService.findOne(locale, currency, code);
    }
    
    @PostMapping("/Product/{locale}/{currency}")
    public List<Product> getProducts(@PathVariable String locale, @PathVariable String currency, @RequestBody final List<String> productCodes) {
    	return productService.findAll(locale, currency, productCodes);
    }
    
}
