package io.nzbee.resources.brand.facet;


import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import io.nzbee.resources.controllers.BrandController;
import io.nzbee.search.facet.EntityFacet;

@Component
public class BrandFacetModelAssembler extends RepresentationModelAssemblerSupport<EntityFacet, BrandFacetModel> {

	public BrandFacetModelAssembler() {
		super(BrandController.class, BrandFacetModel.class);
	}
	
	@Override
	public BrandFacetModel toModel(EntityFacet brand) {
		return new BrandFacetModel(brand);
	}

}