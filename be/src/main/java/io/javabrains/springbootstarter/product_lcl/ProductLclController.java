package io.javabrains.springbootstarter.product_lcl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;


@Controller
public class ProductLclController {

	@Value("${server.port}")
	private String serverPort;

	@Value("${server.host}")
	private String serverHost;
	
	@Autowired
	private ProductLclService productLclService;	
	
	@ResponseBody
	@RequestMapping(method=RequestMethod.GET, value="/ProductLcl")
    public List<ProductLcl> getAllProductLcls(HttpSession session) {
		System.out.println("calling getAllProductLcls");
        return productLclService.getAllProductLcls();	
    }	
	
	@ResponseBody
	@RequestMapping("/ProductLcl/{lcl}/{id}")
	public ProductLcl getProductLcl(@PathVariable Long id, @PathVariable String lcl) {
		System.out.println("calling getProductLcl");
		return productLclService.getProductLcl(id);
	}
	
	@ResponseBody
	@RequestMapping("/ProductLcl/{lcl}/{id}/image")
	public RedirectView getImage(@PathVariable Long id, @PathVariable String lcl) {
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl(serverHost + ":" + serverPort + "/" + productLclService.getProductImageLcl(id));
		return redirectView;
	}
	
	@ResponseBody
	@RequestMapping(method=RequestMethod.POST, value="/{lcl}/ProductLcl")
	public void addProductLcl(ProductLcl productlcl, @PathVariable String lcl) {	
		System.out.println("calling addProductLcl");
		productLclService.addProductLcl(productlcl);
	}
	
	@ResponseBody
	@RequestMapping(method=RequestMethod.PUT, value="/ProductLcl/{lcl}/{id}")
	public void updateProductLcl(@RequestBody ProductLcl productLcl, @PathVariable Long id, @PathVariable String lcl) {
		System.out.println("calling updateProductLcl");
		productLclService.updateProductLcl(productLcl);
	}
	
}
