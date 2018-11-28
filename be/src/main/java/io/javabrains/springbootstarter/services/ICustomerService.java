package io.javabrains.springbootstarter.services;

import io.javabrains.springbootstarter.domain.Party;
import io.javabrains.springbootstarter.domain.PartyPerson;

public interface ICustomerService {

	 Party registerNewPersonCustomer(PartyPerson person) throws CustomerAlreadyExistException;
	
	 boolean customerExist(final String username);
}
