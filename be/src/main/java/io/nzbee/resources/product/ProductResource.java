package io.nzbee.resources.product;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import org.springframework.hateoas.ResourceSupport;
import io.nzbee.domain.product.Product;
import io.nzbee.resources.controllers.ProductController;

public class ProductResource  extends ResourceSupport {

	private final Product product;
	
	public ProductResource(final Product product) {
		this.product = product;
		
	     add(linkTo(methodOn(ProductController.class).get(product.getLclCd(), 
	    		 										  product.getCurrency(), 
	    		 										  product.getProductUPC()))
	    		 									  .withSelfRel());
	}
	

	public Product getProduct() {
		return product;
	}
	
}
