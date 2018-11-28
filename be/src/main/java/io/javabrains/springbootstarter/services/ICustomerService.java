package io.javabrains.springbootstarter.services;

import io.javabrains.springbootstarter.domain.Party;

public interface ICustomerService {

	 boolean customerExist(final String username);

	 //Method takes a customerDTO and input and returns a DTO
	 //The domain model is managed within the method
	 Party registerNewCustomer(final CustomerDTO customer);
}
