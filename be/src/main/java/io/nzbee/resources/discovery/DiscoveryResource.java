package io.nzbee.resources.discovery;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import io.nzbee.resources.controllers.BrandController;
import io.nzbee.resources.controllers.CategoryController;
import io.nzbee.resources.controllers.CustomerController;
import io.nzbee.resources.controllers.ProductController;

public class DiscoveryResource extends RepresentationModel<DiscoveryResource>{

	public DiscoveryResource(String rootURL, String locale, String currency) {
		this.add(linkTo(methodOn(BrandController.class).getBrands(null, null)).withRel("allBrands"));
		this.add(linkTo(methodOn(CategoryController.class).getCategories(null, null)).withRel("allCategories"));
		this.add(linkTo(methodOn(CategoryController.class).getProductCategories(null, null)).withRel("allProductCategories"));
		this.add(linkTo(methodOn(CategoryController.class).getBrandCategories(null, null)).withRel("allBrandCategories"));
		this.add(linkTo(methodOn(CustomerController.class).getCustomer(null)).withRel("customer"));
		this.add(linkTo(methodOn(CustomerController.class).registerNewCustomer(null)).withRel("registerCustomer"));
		this.add(linkTo(methodOn(ProductController.class).getProducts(null, null, 
																	  null, 0, 10, 
																	  null, null, null)).withRel("allProductsForCategory"));
		this.add(new Link(rootURL + "/oauth/token").withRel("accessTokens"));
	}
	
}
