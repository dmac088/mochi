package io.javabrains.springbootstarter.product;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class ProductController {
	
	@Autowired
	private ProductService productService;	
	
	@ResponseBody
	@RequestMapping(method=RequestMethod.GET, value="/Product")
    public List<Product> getAllProducts(HttpSession session) {
		System.out.println("calling getAllProducts");
        return productService.getAllProducts();	
    }	
	
	@ResponseBody
	@RequestMapping("/Product/{id}")
	public Product getProduct(@PathVariable String id) {
		System.out.println("calling getProduct");
		return productService.getProduct(id);
	}
}
