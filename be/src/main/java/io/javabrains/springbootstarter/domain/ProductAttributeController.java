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
public class ProductAttributeController {

	@Value("${server.port}")
	private String serverPort;

	@Value("${server.host}")
	private String serverHost;
	
	@Autowired
	private ProductAttributeService productAttributeService;	

	@ResponseBody
	@RequestMapping("/ProductAttribute")
	public List<ProductAttribute> getProductAttribute() {
		return productAttributeService.getProductAttribute();
	}
	
	@ResponseBody
	@RequestMapping("/ProductAttribute/{id}")
	public Optional<ProductAttribute> getProductAttribute(@PathVariable Long id) {
		return productAttributeService.getProductAttribute(id);
	}
	
	@ResponseBody
	@RequestMapping(method=RequestMethod.POST, value="/ProductAttribute")
	public void addProductAttribute(ProductAttribute product) {	
		System.out.println("calling addProductAttribute");
		productAttributeService.addProductAttribute(product);
	}
	
	@ResponseBody
	@RequestMapping(method=RequestMethod.PUT, value="/ProductAttribute/{id}")
	public void updateProductAttribute(@RequestBody ProductAttribute ProductAttribute, @PathVariable Long id) {
		System.out.println("calling updateProductAttribute");
		productAttributeService.updateProductAttribute(id, ProductAttribute);
	}
	
}
