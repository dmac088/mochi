package io.nzbee.resources.discovery;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import io.nzbee.resources.controllers.BrandController;
import io.nzbee.resources.controllers.CategoryController;
import io.nzbee.resources.controllers.CustomerController;

public class DiscoveryResource extends ResourceSupport {

	public DiscoveryResource(String rootURL, String locale, String currency) {
		this.add(linkTo(methodOn(BrandController.class).getBrands(null, null)).withRel("allBrands"));
		this.add(linkTo(methodOn(CategoryController.class).getCategories(null, null)).withRel("allCategories"));
		this.add(linkTo(methodOn(CategoryController.class).getProductCategories(null, null)).withRel("productCategories"));
		this.add(linkTo(methodOn(CategoryController.class).getBrandCategories(null, null)).withRel("brandCategories"));
		this.add(linkTo(methodOn(CustomerController.class).getCustomer(null)).withRel("customer"));
		this.add(linkTo(methodOn(CustomerController.class).registerNewCustomer(null)).withRel("registerCustomer"));
		this.add(new Link(rootURL + "/oauth/token").withRel("accessTokens"));
	}
	
}
