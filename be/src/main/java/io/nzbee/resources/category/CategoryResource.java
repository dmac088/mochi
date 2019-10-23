package io.nzbee.resources.category;

import org.springframework.hateoas.ResourceSupport;

import io.nzbee.domain.category.Category;
import io.nzbee.domain.controllers.CategoryController;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;

public class CategoryResource extends ResourceSupport {

	//private final Category category;
	
	public CategoryResource(final Category category) {
		
     //   this.category = category;
        //final String id = category.getCode();
        add(ControllerLinkBuilder.linkTo(CategoryController.class).withRel("categories"));
        
       // add(ControllerLinkBuilder.linkTo(methodOn(GymMembershipController.class).all(id)).withRel("memberships"));
       // add(ControllerLinkBuilder.linkTo(methodOn(PersonController.class).get(id)).withSelfRel());
    }
	
}
