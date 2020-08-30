package io.nzbee.resources.bag;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import io.nzbee.dto.bag.BagDTO;
import io.nzbee.resources.controllers.BagController;

@Component
public class BagResourceAssembler extends RepresentationModelAssemblerSupport<BagDTO, BagResource> {

	public BagResourceAssembler() {
		super(BagController.class, BagResource.class);
	}


	@Override
	public BagResource toModel(BagDTO bag) {
		BagResource br = new BagResource(bag);
		br.add(linkTo(methodOn(BagController.class).getCustomerBag(null, null, null)).withSelfRel());
		br.add(linkTo(methodOn(BagController.class).getBagContents(null, null, null)).withRel("bagContents"));
		return br;
	}

}
