package io.nzbee.domain.ports;

import io.nzbee.domain.customer.Customer;
import io.nzbee.dto.customer.CustomerDTO;

public interface ICustomerPortService extends IPortService<Customer> {

	public Customer findByUsername(String userName);
	
	Customer registerNewCustomer(CustomerDTO customer);

	void update(CustomerDTO dto);
	
	void delete(String userName);

	public void addCustomerLocation(Customer c, String clientIP);

	public String validateVerificationToken(String token);

	public Customer getCustomer(String token);

	void authWithoutPassword(Customer customer);
}
