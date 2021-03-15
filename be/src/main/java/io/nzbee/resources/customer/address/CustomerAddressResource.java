package io.nzbee.resources.customer.address;

import org.springframework.hateoas.RepresentationModel;
import com.fasterxml.jackson.annotation.JsonCreator;

import io.nzbee.view.customer.address.CustomerAddressDTOOut;

public class CustomerAddressResource  extends RepresentationModel<CustomerAddressResource> {

	private final CustomerAddressDTOOut data;
	
	@JsonCreator
	public CustomerAddressResource(CustomerAddressDTOOut c) {
		this.data = c;
		
	}
	
	public CustomerAddressDTOOut getData() {
		return data;
	}
	
	
}
