package io.nzbee.domain.ports;

import java.util.Optional;

import io.nzbee.domain.customer.Customer;

public interface ICustomerPortService extends IPortService<Customer> {

	public Optional<Customer> findByUsername(String userName);
	
	public void registerNewCustomer(Customer customer);
	
}
