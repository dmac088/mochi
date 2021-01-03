package io.nzbee.resources.customer;

import org.springframework.hateoas.RepresentationModel;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.nzbee.dto.customer.CustomerDTOOut;

public class CustomerResource  extends RepresentationModel<CustomerResource> {

	private final CustomerDTOOut data;
	
	@JsonCreator
	public CustomerResource(CustomerDTOOut c) {
		this.data = c;
		
	}
	
	public CustomerDTOOut getData() {
		return data;
	}
	
	
}
