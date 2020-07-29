package io.nzbee.resources.product;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import io.nzbee.resources.controllers.ProductController;
import io.nzbee.search.facet.EntityFacet;

@Component
public class PriceFacetResourceAssembler extends RepresentationModelAssemblerSupport<EntityFacet, PriceFacetResource> {

	public PriceFacetResourceAssembler() {
		super(ProductController.class, PriceFacetResource.class);
	}
	
	@Override
	public PriceFacetResource toModel(EntityFacet price) {
		return new PriceFacetResource(price);
	}
    
}