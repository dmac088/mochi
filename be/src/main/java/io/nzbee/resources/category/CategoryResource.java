package io.nzbee.resources.category;

import org.springframework.hateoas.ResourceSupport;

import io.nzbee.domain.category.Category;
import io.nzbee.resource.controllers.CategoryController;
import lombok.Getter;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
//import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Getter
public class CategoryResource extends ResourceSupport {

	private final Category category;
	
	public CategoryResource(final Category category) {
		
        this.category = category;
        //final String id = category.getCode();
        add(linkTo(CategoryController.class).withRel("categories"));
        
       // add(ControllerLinkBuilder.linkTo(methodOn(GymMembershipController.class).all(id)).withRel("memberships"));
       // add(ControllerLinkBuilder.linkTo(methodOn(PersonController.class).get(id)).withSelfRel());
    }
	
}
