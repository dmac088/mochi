package io.nzbee.resources.brand;

import org.springframework.hateoas.ResourceSupport;
import io.nzbee.domain.brand.Brand;

public class BrandResource extends ResourceSupport {

	private final Brand data;
	
	
	public BrandResource(Brand brand) {
		this.data = brand;
		
	}
	
	public Brand getData() {
		return data;
	}
}
