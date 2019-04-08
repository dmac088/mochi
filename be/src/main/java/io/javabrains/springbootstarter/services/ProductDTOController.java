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
    
    @GetMapping("/Product/{lcl}/{curr}/id/{id}")
    public ProductDTO getProduct(@PathVariable String lcl, @PathVariable String curr, @PathVariable Long id) {
    	return productService.getProduct(lcl, curr, id);
    }
    
    @GetMapping("/Product/{lcl}/{curr}/page/{page}/size/{size}/sortBy/{sortBy}")
    public Page<ProductDTO> getProducts(@PathVariable String lcl, @PathVariable String curr, @PathVariable int page, @PathVariable int size, @PathVariable String sortBy) {
    	return productService.getProducts(lcl, curr, page, size, sortBy);
    }
    
    @GetMapping("/Product/{lcl}/{curr}/categoryId/{cat}/preview")
    public List<ProductDTO> getProducts(@PathVariable String lcl, @PathVariable String curr, @PathVariable Long cat) {
    	return productService.getPreviewProductsForCategory(lcl, curr, cat);
    }
   
    @GetMapping("/Product/{lcl}/{curr}/categoryDesc/{category}/page/{page}/size/{size}/sortBy/{sortBy}")
    public Page<ProductDTO> getProducts(@PathVariable String lcl, @PathVariable String curr, @PathVariable String category, @PathVariable int page, @PathVariable int size, @PathVariable String sortBy) {
    	return productService.getProductsForCategory(lcl, curr, category, page, size, sortBy);
    }
    
    @GetMapping("/Product/{lcl}/{curr}/categoryDesc/{category}/brand/{brand}/page/{page}/size/{size}/sortBy/{sortBy}")
    public Page<ProductDTO> getProducts(@PathVariable String lcl, @PathVariable String curr, @PathVariable String category, @PathVariable String brand, @PathVariable int page, @PathVariable int size, @PathVariable String sortBy) {
    	return productService.getProductsForCategoryAndBrand(lcl, curr, category, brand, page, size, sortBy);
    }
    
    @GetMapping("/Product/{lcl}/{curr}/categoryDesc/{category}/maxPrice/{price}/page/{page}/size/{size}/sortBy/{sortBy}")
    public Page<ProductDTO> getProducts(@PathVariable String lcl, @PathVariable String curr, @PathVariable String category, @PathVariable Long price, @PathVariable int page, @PathVariable int size, @PathVariable String sortBy) {
    	return productService.getProductsForCategoryAndPrice(lcl, curr, category, price, page, size, sortBy);
    }
    
    @GetMapping("/Product/{lcl}/{curr}/categoryDesc/{category}/brand/{brand}/maxPrice/{price}/page/{page}/size/{size}/sortBy/{sortBy}")
    public Page<ProductDTO> getProducts(@PathVariable String lcl, @PathVariable String curr, @PathVariable String category, @PathVariable String brand, @PathVariable Long price, @PathVariable int page, @PathVariable int size, @PathVariable String sortBy) {
    	return productService.getProductsForCategoryAndBrandAndPrice(lcl, curr, category, brand, price, page, size, sortBy);
    }
    
    @GetMapping("/Search/{lcl}/{curr}/Category/{categoryCode}/SearchTerm/{term}/Page/{page}/Size/{size}/SortBy/{sortBy}")
    public Page<ProductDTO> search(@PathVariable String lcl, @PathVariable String curr, @PathVariable String categoryCode,@PathVariable String term, @PathVariable int page, @PathVariable int size, @PathVariable String sortBy) {
    	return productService.findProduct(lcl, curr, categoryCode, term, page, size, sortBy);
    }
}
