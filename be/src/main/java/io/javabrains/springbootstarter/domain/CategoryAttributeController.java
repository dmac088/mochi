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
public class CategoryAttributeController {
	
	@Autowired
	private CategoryAttributeService categoryAttributeService;	
	
	@ResponseBody
	@RequestMapping(method=RequestMethod.GET, value="/CategoryAttribute/{lcl}")
    public List<CategoryAttribute> getAllCategoryAttributes(@PathVariable String lcl) {
        return categoryAttributeService.getAllCategoryAttributes(lcl);	
    }	
	
	@ResponseBody
	@RequestMapping(method=RequestMethod.GET, value="/CategoryAttribute/{lcl}/{id}")
	public CategoryAttribute getCategoryAttribute(@PathVariable String lcl, @PathVariable Long id) {
		return categoryAttributeService.getCategoryAttribute(lcl, id);
	}
	
	@ResponseBody
	@RequestMapping(method=RequestMethod.POST, value="/CategoryAttribute")
	public void addCategoryAttribute(CategoryAttribute CategoryAttribute) {	
		System.out.println("calling addProduct");
		categoryAttributeService.addCategory(CategoryAttribute);
	}
	
	@ResponseBody
	@RequestMapping(method=RequestMethod.PUT, value="/CategoryAttribute/{id}")
	public void updateCategoryAttribute(@RequestBody CategoryAttribute CategoryAttribute, @PathVariable Long id) {
		System.out.println("calling updateProduct");
		categoryAttributeService.updateCategory(id, CategoryAttribute);
	}
	
}
