package io.nzbee.resources.bag.item;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import io.nzbee.dto.bag.BagDTO;
import io.nzbee.dto.bag.item.BagItemDTOOut;
import io.nzbee.resources.controllers.BagController;

@Component
public class BagItemResourceAssembler extends RepresentationModelAssemblerSupport<BagItemDTOOut, BagItemResource> {

	public BagItemResourceAssembler() {
		super(BagController.class, BagItemResource.class);
	}


	@Override
	public BagItemResource toModel(BagItemDTOOut bag) {
		BagItemResource br = new BagItemResource(bag);
		//br.add(linkTo(methodOn(BagController.class)(null, null, null)).withSelfRel());
		return br;
	}

}
