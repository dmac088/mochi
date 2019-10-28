package io.nzbee.resources.category;

import org.springframework.hateoas.ResourceSupport;

import io.nzbee.domain.category.BrandCategory;
import io.nzbee.domain.category.Category;
import io.nzbee.domain.category.ProductCategory;
import io.nzbee.resource.controllers.BrandController;
import io.nzbee.resource.controllers.CategoryController;
import io.nzbee.resource.controllers.ProductController;
import lombok.Getter;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Getter
public class CategoryResource extends ResourceSupport {

	private final Category category;
	
	public CategoryResource(String locale, String currency, final Category category) {
		
       this.category = category;
       //final String id = category.getCode();
       // add(linkTo(CategoryController.class).withRel("self"));
       add(linkTo(methodOn(CategoryController.class).get(locale,
        											      currency,
        											      category.getCode())).withSelfRel());
//        
//       add(linkTo(methodOn(CategoryController.class).get(locale,
//			      currency,
//			      category.getCode())).withSelfRel());
        
       if(category instanceof BrandCategory) {
    	   add(linkTo(methodOn(BrandController.class).getBrands(locale, 
    			   												currency, 
    			   												category.getCode()
    			   												)).withRel("brands"));
       } else if (category instanceof ProductCategory) {
    	   add(linkTo(methodOn(ProductController.class).getProducts(locale, 
    			   													currency, 
    			   													category.getCode(),
    			   													//hardcoded values for page and size
    			   													0,
    			   													10
    			   													)).withRel("products"));
       }
       
       // add(ControllerLinkBuilder.linkTo(methodOn(GymMembershipController.class).all(id)).withRel("memberships"));
       // add(ControllerLinkBuilder.linkTo(methodOn(PersonController.class).get(id)).withSelfRel());
    }

	public Category getCategory() {
		return category;
	}
	
}
