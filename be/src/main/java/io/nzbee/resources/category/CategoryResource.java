package io.nzbee.resources.category;

import org.springframework.hateoas.ResourceSupport;

import io.nzbee.domain.category.BrandCategory;
import io.nzbee.domain.category.Category;
import io.nzbee.domain.category.ProductCategory;
import io.nzbee.domain.product.Product;
import io.nzbee.resource.controllers.BrandController;
import io.nzbee.resource.controllers.CategoryController;
import io.nzbee.resource.controllers.ProductController;
import lombok.Getter;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PagedResourcesAssembler;

@Getter
public class CategoryResource extends ResourceSupport {

	@Autowired
	private PagedResourcesAssembler<Product> parAssembler;
	
	
	private final Category category;
	
	public CategoryResource(String locale, String currency, final Category category) {
		
       this.category = category;
       
       add(linkTo(methodOn(CategoryController.class).get(locale,
        											      currency,
        											      category.getCode())).withSelfRel());

       
        
       if(category instanceof BrandCategory) {
    	   add(linkTo(methodOn(BrandController.class).getBrands(locale, 
    			   												currency, 
    			   												category.getCode()
    			   												)).withRel("brands"));
       } else if (category instanceof ProductCategory) {
    	   add(linkTo(methodOn(ProductController.class).getProducts(locale, 
    			   													currency, 
    			   													category.getCode(),
    			   													0,
    			   													10,
    			   													parAssembler)).withRel("products"));
       }
       
    }

	public Category getCategory() {
		return category;
	}
	
}
