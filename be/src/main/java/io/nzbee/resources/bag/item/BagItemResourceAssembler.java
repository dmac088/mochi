package io.nzbee.resources.bag.item;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import io.nzbee.resources.controllers.BagController;
import io.nzbee.resources.controllers.ProductController;
import io.nzbee.view.bag.item.BagItemDTOOut;

@Component
public class BagItemResourceAssembler extends RepresentationModelAssemblerSupport<BagItemDTOOut, BagItemResource> {

	public BagItemResourceAssembler() {
		super(BagController.class, BagItemResource.class);
	}


	@Override
	public BagItemResource toModel(BagItemDTOOut bag) {
		BagItemResource br = new BagItemResource(bag);
		br.add(linkTo(methodOn(BagController.class).getBagContents(null, null, null)).withSelfRel());
		br.add(linkTo(methodOn(ProductController.class).getImageWithMediaType(bag.getItemUPC() + "_1.jpg")).withRel("defaultImage"));
		return br;
	}

}
