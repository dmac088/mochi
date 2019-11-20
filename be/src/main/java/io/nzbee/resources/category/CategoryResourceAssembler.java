package io.nzbee.resources.category;
import org.springframework.hateoas.Link;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;
import io.nzbee.domain.category.Category;
import io.nzbee.resources.controllers.CategoryController;

@Component
public class CategoryResourceAssembler extends ResourceAssemblerSupport<Category, CategoryResource> {

    public CategoryResourceAssembler() {
        super(CategoryController.class, CategoryResource.class);
    }

    @Override
    public CategoryResource toResource(Category category) {
        CategoryResource resource = new CategoryResource(	category.getLocale(), 
        													category.getCurrency(), 
        													category);

        Link selfLink = linkTo(
                methodOn(CategoryController.class).get(	category.getLocale(), 
                										category.getCurrency(), 
                										category.getCode()))
                .withSelfRel();
        resource.add(selfLink);

        return resource;
    }
    
}