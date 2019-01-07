package io.javabrains.springbootstarter.domain;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/api")
public class ProductController {
	
	@Autowired
	private ProductService productService;	
	
	@ResponseBody
	@RequestMapping(method=RequestMethod.GET, value="/Product")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();	
    }		
	
//	@ResponseBody
//	@RequestMapping("/Product/{id}")
//	public Optional<Product> getProduct(@PathVariable Long id) {
//		return productService.getProduct(id);
//	}
	
	@ResponseBody
	@RequestMapping(method=RequestMethod.POST, value="/Product")
	public void addProduct(Product product) {	
		System.out.println("calling addProduct");
		productService.addProduct(product);
	}
	
	@ResponseBody
	@RequestMapping(method=RequestMethod.PUT, value="/Product/{id}")
	public void updateProduct(@RequestBody Product Product, @PathVariable Long id) {
		System.out.println("calling updateProduct");
		productService.updateProduct(id, Product);
	}
	
}
