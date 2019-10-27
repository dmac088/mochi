package io.nzbee.resources.product;

import org.springframework.hateoas.ResourceSupport;
import io.nzbee.domain.product.Product;

public class ProductResource  extends ResourceSupport {

	private final Product product;
	
	public ProductResource(String locale, String currency, final Product product) {
		this.product = product;
		
	}
	
	public Product getProduct() {
		return product;
	}
	
}
