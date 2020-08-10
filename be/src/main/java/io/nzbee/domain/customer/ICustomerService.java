package io.nzbee.domain.customer;

import io.nzbee.domain.IService;
import io.nzbee.dto.customer.CustomerDTO;

public interface ICustomerService extends IService<Customer> {

	boolean customerExist(final String username);

	Customer findByUsername(String userName);

	Customer registerNewCustomer(final CustomerDTO customer);

	void delete(String userName);
	
	void update(CustomerDTO customerDTO);

	void addCustomerLocation(Customer c, String clientIP);

	String validateVerificationToken(String token);

	Customer getCustomer(String token);
	
	void authWithoutPassword(Customer customer);
	
}
