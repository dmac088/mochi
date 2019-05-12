package io.javabrains.springbootstarter.entity;

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
public class ProductAttributeController {

	@Autowired
	private ProductAttributeService productAttributeService;	

	@ResponseBody
	@RequestMapping("/ProductAttribute")
	public List<ProductAttribute> getProductAttribute() {
		return productAttributeService.getProductAttribute();
	}
	
	@ResponseBody
	@RequestMapping("/ProductAttribute/{lcl}")
	public List<ProductAttribute> getProductAttribute(@PathVariable String lcl) {
		return productAttributeService.getProductAttribute(lcl);
	}
	
	@ResponseBody
	@RequestMapping("/ProductAttribute/lcl/{id}")
	public ProductAttribute getProductAttribute(@PathVariable String lcl, @PathVariable Long id) {
		return productAttributeService.getProductAttribute(lcl, id);
	}
	
	@ResponseBody
	@RequestMapping(method=RequestMethod.POST, value="/ProductAttribute")
	public void addProductAttribute(ProductAttribute product) {	
		productAttributeService.addProductAttribute(product);
	}
	
	@ResponseBody
	@RequestMapping(method=RequestMethod.PUT, value="/ProductAttribute/{id}")
	public void updateProductAttribute(@RequestBody ProductAttribute ProductAttribute, @PathVariable Long id) {
		productAttributeService.updateProductAttribute(id, ProductAttribute);
	}
	
}
