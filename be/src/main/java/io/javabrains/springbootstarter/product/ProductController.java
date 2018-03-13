package io.javabrains.springbootstarter.product;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;


@Controller
public class ProductController {

	@Value("${server.port}")
	private String serverPort;

	@Value("${server.host}")
	private String serverHost;
	
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
	public Product getProduct(@PathVariable Long id) {
		System.out.println("calling getProduct");
		return productService.getProduct(id);
	}
	
	@ResponseBody
	@RequestMapping("/Product/images/{id}")
	public RedirectView getImage(@PathVariable Long id) {
		
		System.out.println("calling getImage");
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl(serverHost + ":" + serverPort + "/" + productService.getProductImage(id));
		return redirectView;
	}
	
	
}
