package io.nzbee.resources.brand;

import org.springframework.hateoas.ResourceSupport;
import io.nzbee.domain.brand.Brand;

public class BrandResource extends ResourceSupport {

	private final Brand brand;
	
	
	public BrandResource(String locale, String currency, final Brand brand) {
		this.brand = brand;
		
	}
	
	public Brand getBrand() {
		return brand;
	}
}
