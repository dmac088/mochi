package io.nzbee.resources.brand.search.facet;


import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import io.nzbee.resources.controllers.BrandController;
import io.nzbee.search.facet.EntityFacet;

@Component
public class BrandSearchFacetResourceAssembler extends RepresentationModelAssemblerSupport<EntityFacet, BrandSearchFacetResource> {

	public BrandSearchFacetResourceAssembler() {
		super(BrandController.class, BrandSearchFacetResource.class);
	}
	
	@Override
	public BrandSearchFacetResource toModel(EntityFacet brand) {
		return new BrandSearchFacetResource(brand);
	}

}