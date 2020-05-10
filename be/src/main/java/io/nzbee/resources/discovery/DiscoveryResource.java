package io.nzbee.resources.discovery;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import org.springframework.hateoas.ResourceSupport;
import io.nzbee.resources.controllers.BrandController;
import io.nzbee.resources.controllers.CategoryController;

public class DiscoveryResource extends ResourceSupport {

	public DiscoveryResource(String locale, String currency) {
		this.add(linkTo(methodOn(BrandController.class).getBrands(locale, currency)).withRel("brands"));
		this.add(linkTo(methodOn(CategoryController.class).getCategories(locale, currency)).withRel("categories"));
		this.add(linkTo(methodOn(CategoryController.class).getProductCategories(locale, currency)).withRel("productCategories"));
		this.add(linkTo(methodOn(CategoryController.class).getBrandCategories(locale, currency)).withRel("brandCategories"));
	}
	
}
