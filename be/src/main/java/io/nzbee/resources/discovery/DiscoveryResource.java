package io.nzbee.resources.discovery;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import org.springframework.hateoas.ResourceSupport;
import io.nzbee.resources.controllers.BrandController;

public class DiscoveryResource extends ResourceSupport {

	DiscoveryResource(String locale, String currency) {
		this.add(linkTo(methodOn(BrandController.class).getBrands(locale, currency)).withRel("brands"));
	}
	
}
