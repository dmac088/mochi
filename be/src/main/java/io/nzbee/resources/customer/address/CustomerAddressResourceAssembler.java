package io.nzbee.resources.customer.address;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import io.nzbee.dto.customer.address.CustomerAddressDTOOut;
import io.nzbee.resources.controllers.CustomerController;

@Component
public class CustomerAddressResourceAssembler extends RepresentationModelAssemblerSupport<CustomerAddressDTOOut, CustomerAddressResource> {

	public CustomerAddressResourceAssembler() {
		super(CustomerController.class, CustomerAddressResource.class);
	}

	@Override
	public CustomerAddressResource toModel(CustomerAddressDTOOut c) {
		CustomerAddressResource cr = new CustomerAddressResource(c);
		cr.add(linkTo(methodOn(CustomerController.class).getCustomerAddress(null)).withSelfRel());
		return cr;
	}

}