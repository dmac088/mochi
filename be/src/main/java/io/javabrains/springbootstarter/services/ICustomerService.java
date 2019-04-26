package io.javabrains.springbootstarter.services;
import java.util.List;

import io.javabrains.springbootstarter.domain.Customer;

public interface ICustomerService {

	 boolean customerExist(final String username);

	 //Method takes a customerDTO and input
	 //The domain model is managed within the method
	 void registerNewCustomer(final Customer customer);
	 
	 
	 List<Customer> getCustomers();
	 
	 Customer getCustomer(String userName);
}
