package io.javabrains.springbootstarter.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    
    @GetMapping("/Product/{lcl}")
    public List<ProductDTO> getProducts(@PathVariable String lcl) {
    	return productService.getProducts(lcl);
    }
    
}
