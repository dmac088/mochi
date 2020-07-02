package io.nzbee.resources.category;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import io.nzbee.domain.category.Category;
import io.nzbee.domain.product.Product;
import io.nzbee.resources.controllers.BrandController;
import io.nzbee.resources.controllers.CategoryController;
import io.nzbee.resources.controllers.ProductController;

@Component
public class CategoryResourceAssembler extends RepresentationModelAssemblerSupport<Category, CategoryResource> {

	public CategoryResourceAssembler() {
		super(CategoryController.class, CategoryResource.class);
	}


	@Autowired
	private PagedResourcesAssembler<Product> parAssembler;
	


	@Override
	public CategoryResource toModel(Category category) {
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
																		category.getCategoryCode(),
																		null
																)).withRel("brands"));
		}
		
		if(category.getCategoryType().equals("productcategory")) {
			cr.add(linkTo(methodOn(ProductController.class).getProducts(	
																		null,
																		null,
																		category.getCategoryCode(),
																		0,
																		10,
																		"",
																		null,
																		parAssembler
																)).withRel("products"));
		}
			
		return cr;
	}
    
}