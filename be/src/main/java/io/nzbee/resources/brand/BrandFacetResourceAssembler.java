package io.nzbee.resources.brand;


import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import io.nzbee.resources.controllers.BrandController;
import io.nzbee.search.facet.EntityFacet;

@Component
public class BrandFacetResourceAssembler extends RepresentationModelAssemblerSupport<EntityFacet, BrandFacetResource> {

	public BrandFacetResourceAssembler() {
		super(BrandController.class, BrandFacetResource.class);
	}
	
	@Override
	public BrandFacetResource toModel(EntityFacet brand) {
		return new BrandFacetResource(brand);
	}

}