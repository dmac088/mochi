package io.nzbee.domain.ports;

import io.nzbee.domain.customer.Customer;
import io.nzbee.dto.customer.CustomerDTO;

public interface ICustomerPortService extends IPortService<Customer> {

	public Customer findByUsername(String userName);
	
	void registerNewCustomer(CustomerDTO customer);

	void update(CustomerDTO dto);
	
	void delete(String userName);

	
}
