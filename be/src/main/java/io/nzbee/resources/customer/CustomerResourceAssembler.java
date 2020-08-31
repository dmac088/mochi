package io.nzbee.resources.customer;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import io.nzbee.dto.customer.CustomerDTOOut;
import io.nzbee.resources.controllers.CustomerController;

@Component
public class CustomerResourceAssembler extends RepresentationModelAssemblerSupport<CustomerDTOOut, CustomerResource> {

	public CustomerResourceAssembler() {
		super(CustomerController.class, CustomerResource.class);
	}


	@Override
	public CustomerResource toModel(CustomerDTOOut c) {
		CustomerResource cr = new CustomerResource(c);
		return cr;
	}

}
