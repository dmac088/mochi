package io.nzbee.domain.ports;

import java.util.Optional;

import io.nzbee.domain.customer.Customer;

public interface ICustomerPortService extends IPortService<Customer> {

	Optional<Customer> findByUsername(String userName);
	
}
