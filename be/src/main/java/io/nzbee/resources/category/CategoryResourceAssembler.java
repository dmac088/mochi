package io.nzbee.resources.category;
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
public class CategoryResourceAssembler extends ResourceAssemblerSupport<Category, CategoryResource> {

	public CategoryResourceAssembler() {
		super(CategoryController.class, CategoryResource.class);
	}


	@Autowired
	private PagedResourcesAssembler<Product> parAssembler;
	

	@Override
    public CategoryResource toResource(Category category) {
		CategoryResource cr = new CategoryResource(category);
		
		cr.add(linkTo(methodOn(CategoryController.class).getCategory(		
																		category.getLocale(), 
																		category.getCurrency(), 
																		category.getCategoryCode()
																)).withSelfRel()
		);
		
		if(category.getCategoryType().equals("brandcategory")) { 
			cr.add(linkTo(methodOn(BrandController.class).getBrands(		
																		category.getLocale(),
																		category.getCurrency(), 
																		category.getCategoryCode()
																)).withRel("brands"));
		}
		
		if(category.getCategoryType().equals("productcategory")) {
			cr.add(linkTo(methodOn(ProductController.class).getProducts(	
																		category.getLocale(), 
																	 	category.getCurrency(), 
																	 	category.getCategoryCode(),
																	 	null,
																	 	parAssembler
																)).withRel("products"));
		}
			
		return cr;
    }
    
}