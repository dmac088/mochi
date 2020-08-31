package io.nzbee.resources.product;

import org.springframework.hateoas.RepresentationModel;
import io.nzbee.dto.product.ProductDTOFull;

public class ProductFullResource  extends RepresentationModel<ProductFullResource> {

	private final ProductDTOFull data;
	
	public ProductFullResource(final ProductDTOFull product) {
		this.data = product;
	}
	
	public ProductDTOFull getData() {
		return data;
	}
	
}
