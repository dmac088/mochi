package io.nzbee.resources.category;
import org.springframework.hateoas.Resource;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PagedResourcesAssembler;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;
import io.nzbee.domain.category.Category;
import io.nzbee.domain.product.Product;
import io.nzbee.resources.controllers.BrandController;
import io.nzbee.resources.controllers.CategoryController;
import io.nzbee.resources.controllers.ProductController;

@Component
public class CategoryResourceAssembler extends ResourceAssemblerSupport<Category, Resource<Category>> {

	@Autowired
	private PagedResourcesAssembler<Product> parAssembler;
	
	public CategoryResourceAssembler(Class<?> controllerClass, Class<Resource<Category>> resourceType) {
		super(controllerClass, resourceType);
	}

	@Override
    public Resource<Category> toResource(Category category) {
    	return new Resource<>(
				    			category,
								linkTo(methodOn(CategoryController.class).getCategory(	category.getLocale(), 
																						category.getCurrency(), 
																						category.getCode()
																						)).withSelfRel(),
								linkTo(methodOn(BrandController.class).getBrands(		category.getLocale(), 
																						category.getCurrency(), 
																						category.getCode()
																						)).withRel("brands"),
								linkTo(methodOn(ProductController.class).getProducts(	category.getLocale(), 
																					 	category.getCurrency(), 
																					 	category.getCode(),
																					 	0,
																					 	10,
																					 	parAssembler
																					 	)).withRel("products")
    	);

    }
    
}