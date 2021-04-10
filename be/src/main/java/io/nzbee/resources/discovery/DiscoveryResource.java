package io.nzbee.resources.discovery;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import io.nzbee.resources.controllers.BagController;
import io.nzbee.resources.controllers.CategoryController;
import io.nzbee.resources.controllers.CustomerController;
import io.nzbee.resources.controllers.ProductController;
import io.nzbee.resources.controllers.SearchController;

public class DiscoveryResource extends RepresentationModel<DiscoveryResource> {

	public DiscoveryResource(String rootURL) {

		this.add(linkTo(methodOn(CategoryController.class).getProductCategories(null))
				.withRel("getAllProductCategories"));
		this.add(linkTo(methodOn(CategoryController.class).getBrandCategories(null)).withRel("getAllBrandCategories"));
		this.add(linkTo(methodOn(CustomerController.class).getCustomer(null)).withRel("getCustomer"));
		this.add(
				linkTo(methodOn(CustomerController.class).registerNewCustomer(null, null)).withRel("registerCustomer"));
		this.add(linkTo(methodOn(ProductController.class).getProducts(null, null, null, null, null, null, null))
				.withRel("getAllProductsForCategory"));
//		this.add(linkTo(methodOn(ProductController.class).getProducts(null, null, null)).withRel("getProducts"));
		this.add(linkTo(methodOn(ProductController.class).get(null, null, null)).withRel("getProduct"));
		this.add(linkTo(methodOn(ProductController.class).getShippingProviders(null, null))
				.withRel("getShippingProviders"));
		this.add(linkTo(methodOn(ProductController.class).getShippingDestinations(null, null, null)).withRel("getShippingDestinations"));
		this.add(linkTo(methodOn(ProductController.class).getShippingTypes(null, null)).withRel("getShippingTypes"));
		this.add(linkTo(methodOn(SearchController.class).search(null, null, null, null, null, null, null, null)).withRel("searchProduct"));
		this.add(linkTo(methodOn(SearchController.class).getSuggestions(null, null, null)).withRel("searchSuggestion"));
		this.add(linkTo(methodOn(BagController.class).getCustomerBag(null, null, null)).withRel("getBag"));
		this.add(new Link(rootURL + "/oauth/token").withRel("accessTokens"));

	}

}
