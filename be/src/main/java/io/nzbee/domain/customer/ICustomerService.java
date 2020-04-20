package io.nzbee.domain.customer;

import java.util.Optional;
import io.nzbee.domain.IService;

public interface ICustomerService extends IService<Customer> {

	 boolean customerExist(final String username);

	 //Method takes a customerDTO and input
	 //The domain model calls are managed within the method
	 void registerNewCustomer(final Customer customer);
	

	 Optional<Customer> findByUsername(String userName);
}
