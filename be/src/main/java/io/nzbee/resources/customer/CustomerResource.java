package io.nzbee.resources.customer;

import org.springframework.hateoas.RepresentationModel;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.nzbee.dto.customer.CustomerDTOOut;

public class CustomerResource  extends RepresentationModel<CustomerResource> {

	private final CustomerDTOOut data;
	
	@JsonCreator
	public CustomerResource(@JsonProperty("tag") CustomerDTOOut c) {
		this.data = c;
		
	}
	
	public CustomerDTOOut getData() {
		return data;
	}
	
	
}
