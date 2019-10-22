package io.nzbee.domain.resources;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import io.nzbee.domain.category.Category;
import io.nzbee.domain.controllers.CategoryController;
import lombok.Getter;


@Getter 
public class CategoryResource extends ResourceSupport {
	
//	private final Category category;
//	
//    public CategoryResource(final Category category) {
//        this.category = category;
//        final String id = category.getCode();
//        add(ControllerLinkBuilder.linkTo(CategoryController.class).withRel("categories"));
//        //add(linkTo(methodOn(CategoryController.class).all(id)).withRel("categorues"));
//        add(ControllerLinkBuilder
//        		.linkTo(ControllerLinkBuilder.methodOn(CategoryController.class)
//        				
//        				.getCategory(category.getLocale(), 
//        							 category.getCurrenyCode(), 
//        							 category.getCode())
//        	).withSelfRel());
//        
//    }
}
