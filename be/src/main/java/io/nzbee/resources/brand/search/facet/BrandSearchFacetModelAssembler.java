package io.nzbee.resources.brand.search.facet;


import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import io.nzbee.resources.controllers.BrandController;
import io.nzbee.search.facet.EntityFacet;

@Component
public class BrandSearchFacetModelAssembler extends RepresentationModelAssemblerSupport<EntityFacet, BrandSearchFacetModel> {

	public BrandSearchFacetModelAssembler() {
		super(BrandController.class, BrandSearchFacetModel.class);
	}
	
	@Override
	public BrandSearchFacetModel toModel(EntityFacet brand) {
		return new BrandSearchFacetModel(brand);
	}

}