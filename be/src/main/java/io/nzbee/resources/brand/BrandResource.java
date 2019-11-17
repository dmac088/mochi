package io.nzbee.resources.brand;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import org.springframework.hateoas.ResourceSupport;
import io.nzbee.domain.brand.Brand;
import io.nzbee.resources.controllers.BrandController;

public class BrandResource extends ResourceSupport {

	private final Brand brand;
	
	
	public BrandResource(String locale, String currency, final Brand brand) {
		this.brand = brand;
		
		add(linkTo(methodOn(BrandController.class).get(locale,
			      currency,
			      brand.getCode())).withSelfRel());
		
	}
	
	public Brand getBrand() {
		return brand;
	}
}
