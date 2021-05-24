package io.nzbee.resources.brand.browse.facet;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import io.nzbee.resources.controllers.BrandController;
import io.nzbee.view.product.brand.facet.BrandFacetView;

@Component
public class BrandBrowseFacetModelAssembler extends RepresentationModelAssemblerSupport<BrandFacetView, BrandBrowseFacetModel> {

	public BrandBrowseFacetModelAssembler() {
		super(BrandController.class, BrandBrowseFacetModel.class);
	}


	@Override
	public BrandBrowseFacetModel toModel(BrandFacetView brand) {
		BrandBrowseFacetModel br = new BrandBrowseFacetModel(brand);
		return br;
	}

    @Override
    public CollectionModel<BrandBrowseFacetModel> toCollectionModel(Iterable<? extends BrandFacetView> views) 
    {
        CollectionModel<BrandBrowseFacetModel> tags = super.toCollectionModel(views);
         
        tags.add(linkTo(methodOn(BrandController.class).getBrandFacets(null, null, null, null)).withSelfRel());
         
        return tags;
    }

}
