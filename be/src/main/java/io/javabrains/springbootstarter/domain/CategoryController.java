package io.javabrains.springbootstarter.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/api")
public class CategoryController {
	
	@Autowired
	private CategoryService productCategoryService;	
	
	@ResponseBody
	@RequestMapping(method=RequestMethod.POST, value="/ProductCategory")
	public void addProductCategory(Category productCategory) {	
		System.out.println("calling addProduct");
		productCategoryService.addProductCategory(productCategory);
	}
	
	@ResponseBody
	@RequestMapping(method=RequestMethod.PUT, value="/ProductCategory/{id}")
	public void updateProductCategory(@RequestBody Category productCategory, @PathVariable Long id) {
		System.out.println("calling updateProduct");
		productCategoryService.updateProductCategory(id, productCategory);
	}
	
}
