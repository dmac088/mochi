package io.nzbee.domain.customer;

import io.nzbee.domain.IDomainService;
import io.nzbee.view.customer.CustomerDTOIn;

public interface ICustomerService extends IDomainService<Customer> {

	boolean customerExist(final String username);

	Customer findByUsername(String userName);

	Customer registerNewCustomer(final CustomerDTOIn customer);

	void delete(String userName);
	
	void update(CustomerDTOIn customerDTO);

	void addCustomerLocation(Customer c, String clientIP);

	String validateVerificationToken(String token);

	void authWithoutPassword(String token);
	
}
