package io.nzbee.resources.discovery;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import io.nzbee.resources.controllers.BrandController;
import io.nzbee.resources.controllers.CategoryController;
import io.nzbee.resources.controllers.CustomerController;
import io.nzbee.resources.controllers.ProductController;
import io.nzbee.resources.controllers.SearchController;

public class DiscoveryResource extends RepresentationModel<DiscoveryResource>{

	public DiscoveryResource(String rootURL) {
		this.add(linkTo(methodOn(BrandController.class).getBrands(null, null)).withRel(					"getAllBrands"));
		this.add(linkTo(methodOn(CategoryController.class).getCategories(null, null)).withRel(			"getAllCategories"));
		this.add(linkTo(methodOn(CategoryController.class).getProductCategories(null, null)).withRel(	"getAllProductCategories"));
		this.add(linkTo(methodOn(CategoryController.class).getBrandCategories(null, null)).withRel(		"getAllBrandCategories"));
		this.add(linkTo(methodOn(CustomerController.class).getCustomer(null)).withRel(					"getCustomer"));
		this.add(linkTo(methodOn(CustomerController.class).registerNewCustomer(null)).withRel(			"registerCustomer"));
		this.add(linkTo(methodOn(ProductController.class).getProducts(null, 
																	  null, 
																	  null, 
																	  null, 
																	  null, 
																	  null, 
																	  null)).withRel(					"getAllProductsForCategory"));
		this.add(linkTo(methodOn(ProductController.class).getProducts(null, null, null)).withRel(		"getProducts"));
		this.add(linkTo(methodOn(ProductController.class).get(null, null, null)).withRel(				"getProduct"));
		this.add(linkTo(methodOn(SearchController.class).search(null, null, null, null, null, null, null, null)
																	  ).withRel(								"searchProduct"));
		this.add(linkTo(methodOn(SearchController.class).getSuggestions(null, null, null)
																	  ).withRel(								"searchSuggestion"));
		this.add(new Link(rootURL + "/oauth/token").withRel("accessTokens"));
		
	}
	
}
