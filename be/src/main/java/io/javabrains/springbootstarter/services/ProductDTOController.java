package io.javabrains.springbootstarter.services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ProductDTOController {

    @Autowired
    private IProductDTOService productService;

    public ProductDTOController() {
        super();
    }
    
    @GetMapping("/Product/{lcl}/id/{id}")
    public ProductDTO getProduct(@PathVariable String lcl, @PathVariable Long id) {
    	return productService.getProduct(lcl, id);
    }
    
    @GetMapping("/Product/{lcl}/page/{page}/size/{size}/sortBy/{sortBy}")
    public Page<ProductDTO> getProducts(@PathVariable String lcl,@PathVariable int page, @PathVariable int size, @PathVariable String sortBy) {
    	return productService.getProducts(lcl, page, size, sortBy);
    }
    
    @GetMapping("/Product/{lcl}/cat/{cat}/page/{page}/size/{size}/sortBy/{sortBy}")
    public Page<ProductDTO> getProducts(@PathVariable String lcl,@PathVariable Long cat, @PathVariable int page, @PathVariable int size, @PathVariable String sortBy) {
    	return productService.getAllProductsForCategory(lcl, cat, page, size, sortBy);
    }
    
    @GetMapping("/Product/{lcl}/cat/{cat}/preview")
    public List<ProductDTO> getProducts(@PathVariable String lcl, @PathVariable Long cat) {
    	return productService.getPreviewProductsForCategory(lcl, cat);
    }
    
    @GetMapping("/Product/{lcl}/featured")
    public List<ProductDTO> getProducts(@PathVariable String lcl) {
    	return productService.getFeaturedProducts(lcl);
    }
    
}
