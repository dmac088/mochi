package io.javabrains.springbootstarter.domain;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/api")
public class ProductController {

	@Value("${server.port}")
	private String serverPort;

	@Value("${server.host}")
	private String serverHost;
	
	@Autowired
	private ProductService productService;	
	
	@ResponseBody
	@RequestMapping(method=RequestMethod.GET, value="/Product/{lcl}")
    public Page<Product> getAllProducts(@PathVariable String lcl) {
        return productService.getAllProducts(lcl);	
    }	
	
	@ResponseBody
	@RequestMapping(method=RequestMethod.GET, value="/Product/{lcl}/page/{pageNo}")
    public Page<Product> getAllProducts(@PathVariable String lcl, @PathVariable String pageNo) {
        return productService.getAllProducts(lcl);	
    }	
	
	
	@ResponseBody
	@RequestMapping(method=RequestMethod.GET, value="/Product/{lcl}/{catid}")
    public List<Product> getAllProducts(@PathVariable String lcl, @PathVariable Long catid) {
        return productService.getAllProducts(lcl, catid);	
    }	
	
//	@ResponseBody
//	@RequestMapping(method=RequestMethod.GET, value="/Product/{lcl}/{catid}/page/{pageNo}")
//    public List<Product> getAllProducts(@PathVariable String lcl, @PathVariable Long catid, @PathVariable String pageNo) {
//        return productService.getAllProducts(lcl, catid, pageNo);	
//    }	
	
	@ResponseBody
	@RequestMapping("/Product/id/{id}")
	public Optional<Product> getProduct(@PathVariable Long id) {
		return productService.getProduct(id);
	}
	
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
