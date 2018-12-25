package io.javabrains.springbootstarter.domain;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/api")
public class ProductCategoryController {

	@Value("${server.port}")
	private String serverPort;

	@Value("${server.host}")
	private String serverHost;
	
	@Autowired
	private ProductCategoryService productCategoryService;	
	
	@ResponseBody
	@RequestMapping(method=RequestMethod.GET, value="/ProductCategory/{lcl}")
    public List<ProductCategory> getAllProductCategories(@PathVariable String lcl) {
		System.out.println("calling getAllProductsForLcl");
        return productCategoryService.getAllProductCategories();	
    }	
	
	@ResponseBody
	@RequestMapping("/ProductCategory/{id}")
	public Optional<ProductCategory> getProductCategory(@PathVariable Long id) {
		System.out.println("calling getProduct");
		return productCategoryService.getProductCategory(id);
	}
	
	
	@ResponseBody
	@RequestMapping(method=RequestMethod.POST, value="/ProductCategory")
	public void addProductCategory(ProductCategory productCategory) {	
		System.out.println("calling addProduct");
		productCategoryService.addProductCategory(productCategory);
	}
	
	@ResponseBody
	@RequestMapping(method=RequestMethod.PUT, value="/ProductCategory/{id}")
	public void updateProductCategory(@RequestBody ProductCategory productCategory, @PathVariable Long id) {
		System.out.println("calling updateProduct");
		productCategoryService.updateProductCategory(id, productCategory);
	}
	
}
