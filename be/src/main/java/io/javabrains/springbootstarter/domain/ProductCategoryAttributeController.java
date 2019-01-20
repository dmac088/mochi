package io.javabrains.springbootstarter.domain;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/api")
public class ProductCategoryAttributeController {
	
	@Autowired
	private ProductCategoryAttributeService productCategoryAttributeService;	
	
	@ResponseBody
	@RequestMapping(method=RequestMethod.GET, value="/ProductCategoryAttribute/{lcl}")
    public List<ProductCategoryAttribute> getAllProductCategoryAttributes(@PathVariable String lcl) {
        return productCategoryAttributeService.getAllProductCategoryAttributes(lcl);	
    }	
	
	@ResponseBody
	@RequestMapping(method=RequestMethod.GET, value="/ProductCategoryAttribute/{lcl}/{id}")
	public Optional<ProductCategoryAttribute> getProductCategoryAttribute(@PathVariable String lcl, @PathVariable Long id) {
		return productCategoryAttributeService.getProductCategoryAttribute(lcl, id);
	}
	
	@ResponseBody
	@RequestMapping(method=RequestMethod.POST, value="/ProductCategoryAttribute")
	public void addProductCategoryAttribute(ProductCategoryAttribute productCategoryAttribute) {	
		System.out.println("calling addProduct");
		productCategoryAttributeService.addProductCategory(productCategoryAttribute);
	}
	
	@ResponseBody
	@RequestMapping(method=RequestMethod.PUT, value="/ProductCategoryAttribute/{id}")
	public void updateProductCategoryAttribute(@RequestBody ProductCategoryAttribute productCategoryAttribute, @PathVariable Long id) {
		System.out.println("calling updateProduct");
		productCategoryAttributeService.updateProductCategory(id, productCategoryAttribute);
	}
	
}
