package io.nzbee.resources.category;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import io.nzbee.domain.category.Category;
import io.nzbee.resources.controllers.BrandController;
import io.nzbee.resources.controllers.CategoryController;
import io.nzbee.resources.controllers.ProductController;
import io.nzbee.resources.controllers.TagController;

@Component
public class CategoryResourceAssembler extends RepresentationModelAssemblerSupport<Category, CategoryResource> {

	public CategoryResourceAssembler() {
		super(CategoryController.class, CategoryResource.class);
	}
	
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
																		null,
																		null,
																		null,
																		null
																)).withRel("products"));
			
			cr.add(linkTo(methodOn(BrandController.class).getBrands(		
																		category.getLocale(),
																		category.getCurrency(), 
																		category.getCategoryCode(),
																		null
																)).withRel("brands"));
			
			cr.add(linkTo(methodOn(TagController.class).getTags(		
																		category.getLocale(),
																		category.getCurrency(), 
																		category.getCategoryCode(),
																		null
																)).withRel("tags"));
			
			cr.add(linkTo(methodOn(CategoryController.class).getChildCategories(
																		category.getLocale(),
																		category.getCurrency(),  
																		category.getCategoryCode(), 
																		null
																)).withRel("children"));
					
			
			cr.add(linkTo(methodOn(CategoryController.class).getChildCategoryFacets(
																		category.getLocale(),
																		category.getCurrency(),  
																		category.getCategoryCode(), 
																		null
																)).withRel("childFacets"));

			
			cr.add(linkTo(methodOn(CategoryController.class).getMaxPrice(
																		category.getLocale(),
																		category.getCurrency(),  
																		category.getCategoryCode(), 
																		null
																)).withRel("maxPrice"));
			
			
		}
			
		return cr;
	}
    
}