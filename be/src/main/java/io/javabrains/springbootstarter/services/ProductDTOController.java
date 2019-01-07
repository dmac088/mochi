package io.javabrains.springbootstarter.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ProductDTOController {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private IProductDTOService productService;

    public ProductDTOController() {
        super();
    }
    
    @GetMapping("/Product/{lcl}/page/{page}/size/{size}")
    public Page<ProductDTO> getProducts(@PathVariable String lcl,@PathVariable int page,@PathVariable int size) {
    	return productService.getProducts(lcl, page, size);
    }
    
    
    @GetMapping("/Product/{lcl}/cat/{cat}/page/{page}/size/{size}")
    public Page<ProductDTO> getProducts(@PathVariable String lcl,@PathVariable Long cat, @PathVariable int page,@PathVariable int size) {
    	return productService.getProducts(lcl, cat, page, size);
    }
    
}
